/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class MealTypePrinter implements Visitor<MealType> {

    @Override
    public void visit(MealType visitee) {
        System.out.printf("%-30s\n", visitee.description());
    }

    @Override
    public void beforeVisiting(MealType visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(MealType visitee) {
        // nothing to do
    }
}
