/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.ecafeteria.user.consoleapp.presentation.meals.BookingAction;
import eapli.ecafeteria.user.consoleapp.presentation.meals.CancelBookingAction;
import eapli.ecafeteria.user.consoleapp.presentation.meals.ConsultReservesAction;
import eapli.ecafeteria.user.consoleapp.presentation.menus.ListMenusAction;
import eapli.ecafeteria.user.consoleapp.presentation.rating.MealRatingAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int RATING_OPTION = 4;

    // BOOKINGS MENU
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int CONSULT_RESERVES_OPTION = 3;
    private static final int CANCEL_BOOKING_OPTION = 4;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;

    // RATING MENU AND CALORIES
    private static final int MEAL_RATING_OPTION = 1;
    
    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.add(new SubMenu(BOOKINGS_OPTION, bookingsMenu, new ShowVerticalSubMenuAction(bookingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu accountMenu = buildAccountMenu();
        mainMenu.add(new SubMenu(ACCOUNT_OPTION, accountMenu, new ShowVerticalSubMenuAction(accountMenu)));
        // TODO add menu options

        mainMenu.add(VerticalSeparator.separator());
        
        final Menu ratingMenu = buildRatingMenu();
        mainMenu.add(new SubMenu(RATING_OPTION, ratingMenu, new ShowVerticalSubMenuAction(ratingMenu)));
        
        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAccountMenu() {
        final Menu menu = new Menu("Account");
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, "List movements", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.add(new MenuItem(LIST_MENUS_OPTION, "List menus", new ListMenusAction()));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a meal", new BookingAction() ) );
        menu.add(new MenuItem(CONSULT_RESERVES_OPTION, "Consult reserves in next n days", new ConsultReservesAction() ) );
        menu.add(new MenuItem(CANCEL_BOOKING_OPTION, "Cancel Bookings", new CancelBookingAction() ) );
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }
    
    
    // SUBMENU Ratings/Calorias da pagina 7 do enunciado (Hugo)
    private Menu buildRatingMenu(){
        final Menu menu = new Menu("Rating/Calories");
        menu.add(new MenuItem(MEAL_RATING_OPTION, "Rate a meal", new MealRatingAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }
    

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
