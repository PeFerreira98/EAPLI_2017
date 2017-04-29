package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

public class MealPrinter implements Visitor<Meal> {

	@Override
	public void visit(Meal visitee) {
        System.out.printf("%-12s%-10s%-30s%-12s%-30s\n", 
        		visitee.date().toString(), 
        		visitee.dish().dishType().description(),
        		visitee.dish().name(),
        		visitee.mealType().description(),
        		visitee.menu().id()
        		
        		);

	}

}
