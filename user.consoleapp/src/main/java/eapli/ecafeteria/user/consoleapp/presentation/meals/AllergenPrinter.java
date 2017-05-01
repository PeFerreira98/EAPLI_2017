/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Hugo & Pedro G3
 */
public class AllergenPrinter implements Visitor <Allergen> {

    @Override
    public void visit(Allergen visitee) {
        System.out.printf("%-30s\n", visitee.id());
    }

    @Override
    public void beforeVisiting(Allergen visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Allergen visitee) {
        // nothing to do
    }
    
}
