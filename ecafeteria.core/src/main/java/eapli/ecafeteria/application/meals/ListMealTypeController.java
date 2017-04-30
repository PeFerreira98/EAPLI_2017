package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;

public class ListMealTypeController implements Controller {

	private ListMealTypeService svc = new ListMealTypeService();
	
	public Iterable<MealType> allMealTypes(){
		return this.svc.allMealTypes();
	}
}
