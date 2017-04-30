package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ListMealTypeController;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListMealTypeUI extends AbstractListUI<MealType> {

	private final ListMealTypeController theController = new ListMealTypeController();
		
	protected Controller Controller(){
		return this.theController;
	}
	
	@Override
	protected Iterable<MealType> listOfElements() {
		return this.theController.allMealTypes();
	}

	@Override
	protected Visitor<MealType> elementPrinter() {
		return new MealTypePrinter();
	}

	@Override
	protected String elementName() {
		return "MealType";
	}

}
