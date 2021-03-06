/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class MealPrinter implements Visitor<Meal> {

    @Override
    public void visit(Meal visitee) {
        System.out.println(DateTime.format(visitee.date()) + "   " 
                        + visitee.dish().dishType().description() + "   " 
                        + visitee.dish().name() + "   " 
                        + visitee.mealType().description());
    }

    public void beforeVisiting(Meal visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Meal visitee) {
        // nothing to do
    }
}
