package eapli.ecafeteria.user.consoleapp.presentation.menus;

import eapli.ecafeteria.application.menus.ListMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.user.consoleapp.presentation.meals.MealPrinter;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ListMenusUI extends AbstractUI {

	private final ListMenuController controller = new ListMenuController();
	
	protected Controller controller() {
		return this.controller;
	}
	
	@Override
	protected boolean doShow() {
		final Iterable<Menu> menus = this.controller.publishedMenus();
		
		System.out.println("\nChoose a Menu:");
		final SelectWidget<Menu> menuSelector = new SelectWidget<>("Menus", menus, new MenuPrinter());
		menuSelector.show();
		final Menu menu = menuSelector.selectedElement();
		
		if(menu != null){
			
			final Iterable<Meal> meals = this.controller.mealsBymenu(menu);
			final SelectWidget<Meal> mealSelector = new SelectWidget<>(menu.name() + " Meals", meals, new MealPrinter());
			mealSelector.show();
			final Meal meal = mealSelector.selectedElement();
			
		}
		
		return false;
	}

	@Override
	public String headline() {
		return "Listing Menus";
	}

}
