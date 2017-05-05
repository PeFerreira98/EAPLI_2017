/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterBatchController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

/**
 *
 * @author Pedro Tedim
 */
public class RegisterBatchUI extends AbstractUI {
    
    private final RegisterBatchController theController = new RegisterBatchController();
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final String batchCode = Console.readLine("Batch Code:");
        final String description = Console.readLine("Description:");
        final String matRef = Console.readLine("Material Reference:");

        try {
            this.theController.registerBatch(batchCode, description, matRef);
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("That acronym is already in use.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Batch";
    }
}
