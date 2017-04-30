package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.backoffice.consoleapp.presentation.menus.MenuPrinter;
import java.util.Calendar;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

public class RegisterMealUI extends AbstractUI {
	
	private final RegisterMealController theController = new RegisterMealController();
	
	protected Controller controller() {
        return this.theController;
    }

	@Override
	protected boolean doShow() {
		final Iterable<Menu> menus = this.theController.listMenus();
		final Iterable<MealType> mealTypes = this.theController.listMealTypes();
		final Iterable<Dish> dishes = this.theController.listDish();
		
		final SelectWidget<Menu> menuSelector = new SelectWidget<>(menus, new MenuPrinter());
		menuSelector.show();
		final Menu menu = menuSelector.selectedElement();
		
		final SelectWidget<Dish> dishSelector = new SelectWidget<>(dishes, new DishPrinter());
		dishSelector.show();
		final Dish dish = dishSelector.selectedElement();
		
		final SelectWidget<MealType> mealTypeSelector = new SelectWidget<>(mealTypes, new MealTypePrinter());
		mealTypeSelector.show();
		final MealType mealType = mealTypeSelector.selectedElement();
		
		final String desc = Console.readLine("Description");
		
		final Calendar date = Console.readCalendar("Meal Date (dd-mm-aaaa)");
		
		try {			
			this.theController.registerMeal(dish, mealType, menu, date, desc);
			
		} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a meal which already exists in the database.");
        }
		
		return false;
	}

	@Override
	public String headline() {
		return "Register Meal";
	}

}
