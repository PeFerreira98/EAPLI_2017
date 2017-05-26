/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.cafeteria.EditNutricionalProfileController;
import eapli.ecafeteria.application.meals.ListAllergenService;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Silvestre
 */
public class EditNutricionalProfileUI extends AbstractUI {

    private final EditNutricionalProfileController theController = new EditNutricionalProfileController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final int dailyCalories = Console.readInteger("Daily Calories:");
        final int dailySalt = Console.readInteger("Daily Salt:");
        final int weeklyCalories = Console.readInteger("Weekly Calories:");
        final int weeklySalt = Console.readInteger("Weekly Salt:");

        try {
            this.theController.editProfile(dailyCalories, dailySalt, weeklyCalories, weeklySalt);

            String option;
            int op;
            Iterable<Allergen> availableAllergens = new ListAllergenService().allDishAllergens();
            while ((op = Console.readInteger("1 - Add Allergen\n2 - Remove Allergen\n0- Exit")) != 0) {
                System.out.println("Allergen List: ");
                for (Allergen a : availableAllergens) {
                    System.out.println(a.getName());
                }
                option = Console.readLine("Choose Allergen: (exit to end)");
                if (op == 1) {

                    for (Allergen a : availableAllergens) {
                        if (option.equalsIgnoreCase(a.getName())) {
                            this.theController.addAllergen(a);
                        }
                    }
                }

                if (op == 2) {

                    for (Allergen a : availableAllergens) {
                        if (option.equalsIgnoreCase(a.getName())) {
                            this.theController.removeAllergen(a);
                        }
                    }

                }
            }
            System.out.println("Nutricional Profile Successfully Registered");
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterNutricionalProfileUI.class.getName()).log(Level.SEVERE, "Error Registering Nutricional Profile", ex);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Edit Nutricional Profile";
    }
}
