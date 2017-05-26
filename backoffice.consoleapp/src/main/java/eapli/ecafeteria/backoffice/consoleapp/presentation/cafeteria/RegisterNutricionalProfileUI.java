/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.application.cafeteria.RegisterNutricionalProfileController;
import eapli.ecafeteria.application.meals.ListAllergenService;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
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
public class RegisterNutricionalProfileUI extends AbstractUI {

    private final RegisterNutricionalProfileController theController = new RegisterNutricionalProfileController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final int dailyCalories = Console.readInteger("Daily Calories:");
        final int dailySalt = Console.readInteger("Daily Salt:");
        final int weeklyCalories = Console.readInteger("Weekly Calories:");
        final int weeklySalt = Console.readInteger("Weekly Salt:");

        //TODO: Show Allergen List
        String option;
        
        Iterable<Allergen> availableAllergens = new ListAllergenService().allDishAllergens();
        List<Allergen> allergens = new ArrayList<>();
        while (!(option = Console.readLine("Choose Allergen: (exit to end)")).equalsIgnoreCase("exit")) {
            for (Allergen a : availableAllergens) {
                if(option.equalsIgnoreCase(a.getName()) && !allergens.contains(a)){
                    allergens.add(a);
                }
            }
        }

        try {
            NutricionalProfile newNutricionalProfile = this.theController.registerNutricionalProfile(new CafeteriaUserService().obtainCurrentCafeteriaUser(), dailyCalories, dailySalt, weeklyCalories, weeklySalt);
            this.theController.registerNutricionalProfileAllergens(newNutricionalProfile, allergens);
            this.theController.registerNutricionalProfileAllergens(newNutricionalProfile, allergens);
            System.out.println("Nutricional Profile Successfully Registered");
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterNutricionalProfileUI.class.getName()).log(Level.SEVERE, "Error Registering Nutricional Profile", ex);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Regist Nutricional Profile";
    }
}
