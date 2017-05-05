/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;


import eapli.ecafeteria.application.kitchen.RegisterMealBatchController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.DateTime;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Pedro Tedim
 */
public class RegisterMealBatchUI extends AbstractUI{
    
    private final RegisterMealBatchController theController = new RegisterMealBatchController();
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        
        
//        final Calendar mealDate =  Console.readCalendar("Meal Date (DD-MM-YYYY):", "DD-MM-YYYY");
        final Calendar mealDate =  DateTime.parseDate(Console.readLine("Meal Date (DD-MM-YYYY):"));
//        final Date mealDate =  Console.readDate("Meal Date (DD-MM-YYYY):", "DD-MM-YYYY");
        final String batchCode = Console.readLine("Batch Code:");
        final String matRef = Console.readLine("Material Reference:");

        try {
            this.theController.registerMealBatch(mealDate, matRef, batchCode);
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("That acronym is already in use.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Meal Batch";
    }
    
}
