/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Tedim
 */
public class MealBatchPrinter implements Visitor<MealBatch>{
    @Override
    public void visit(MealBatch visitee) {
        System.out.printf("%-10s\n", visitee.date().getTime());
    }

    @Override
    public void beforeVisiting(MealBatch visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(MealBatch visitee) {
        // nothing to do
    }
    
}
