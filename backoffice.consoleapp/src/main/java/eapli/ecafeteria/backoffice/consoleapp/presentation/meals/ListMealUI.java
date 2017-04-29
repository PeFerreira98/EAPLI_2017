package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ListMealController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListMealUI extends AbstractListUI<Meal>{

	private final ListMealController theController = new ListMealController();
	
	protected Controller Controller(){
		return this.theController;
	}
	
	@Override
	protected Iterable<Meal> listOfElements() {
		return this.theController.allMeals();
	}

	@Override
	protected Visitor<Meal> elementPrinter() {
		return new MealPrinter();
	}

	@Override
	protected String elementName() {
		return "Meal";
	}

}
