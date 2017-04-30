package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

public class MealTypePrinter implements Visitor<MealType> {

	@Override
	public void visit(MealType visitee) {
		System.out.printf("%-10s%-30s%-4s\n", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
	}

}
