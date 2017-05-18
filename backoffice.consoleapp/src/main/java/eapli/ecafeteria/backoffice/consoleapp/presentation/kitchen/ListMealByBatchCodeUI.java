package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ListMealByBatchCodeController;
import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import eapli.util.io.Console;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro Tedim
 */
public class ListMealByBatchCodeUI extends AbstractListUI<MealBatch> {

    private final ListMealByBatchCodeController theController = new ListMealByBatchCodeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final String batchCode = Console.readLine("Batch Code:");

        try {

            List<MealBatch> list = this.theController.searchMealByBatchCode(batchCode);
            for (MealBatch mealBatch : list) {
                Calendar date = mealBatch.date();
                System.out.printf("%-10s\n", date.getTime());
                
            }
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("That acronym is already in use.");
        }
        
        
        

        return false;
    }

    @Override
    public String headline() {
        return "List Meal by Batch Code";
    }

    @Override
    protected Iterable<MealBatch> listOfElements() {
        return this.theController.all();
    }

    @Override
    protected Visitor<MealBatch> elementPrinter() {
        return new MealBatchPrinter();
    }

    @Override
    protected String elementName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
