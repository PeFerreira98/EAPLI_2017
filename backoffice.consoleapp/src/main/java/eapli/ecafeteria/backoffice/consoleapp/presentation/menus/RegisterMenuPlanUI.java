/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.menus.RegisterMenuPlanController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.Iterator;

public class RegisterMenuPlanUI extends AbstractUI {

    private final RegisterMenuPlanController theController = new RegisterMenuPlanController();
    private Menu selectMenu = null;
    private Meal selectMeal = null;

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<Menu> menus = theController.getPublishedMenus();

        SelectWidget<Menu> swMenu = new SelectWidget<>("Choose a menu:", menus, new MenuPrinter());
        swMenu.show();

        selectMenu = swMenu.selectedElement();
        if (selectMenu == null) {
            return false;
        }

        /*
        final Iterable<MenuPlan> mpl =  theController.menuPlanByMenu(selectMenu.id());
        if( mpl != null )
            System.out.println("mpl != null");
        else
            System.out.println("mpl == null");*/
        final Iterable<Meal> meals = theController.getMealByMenu(selectMenu);
        Iterator<Meal> iteratorMeal = meals.iterator();
        while (iteratorMeal.hasNext()) {

            SelectWidget<Meal> swMeal = new SelectWidget("Choose a Meal:", meals, new MealPrinter());

            swMeal.show();

            selectMeal = swMeal.selectedElement();

            if (swMeal.selectedOption() == 0) {
                break;
            }

            final int quantidade = Console.readInteger("Quantity:");
            try {
                this.theController.CreateMenuPlan(selectMenu, selectMeal, quantidade);
            } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                System.out.println("You tried to enter a meal plan which already exists in the data base");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Menu Plan";
    }

}
