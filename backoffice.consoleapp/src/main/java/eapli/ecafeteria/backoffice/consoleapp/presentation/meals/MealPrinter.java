package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import java.text.SimpleDateFormat;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

public class MealPrinter implements Visitor<Meal> {

	@Override
	public void visit(Meal visitee) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-12s%-30s%-30s%-12s%-30s\n", 
        		format1.format(visitee.date().getTime()),
        		visitee.dish().dishType().description(),
        		visitee.dish().name(),
        		visitee.mealType().description(),
        		visitee.menu().id()
        		);

	}

}
