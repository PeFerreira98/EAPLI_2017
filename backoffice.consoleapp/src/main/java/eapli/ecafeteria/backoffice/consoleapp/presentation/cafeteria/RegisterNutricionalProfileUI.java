/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.authz;

import eapli.ecafeteria.application.cafeteria.RegisterNutricionalProfileController;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Silvestre
 */
public class RegisterNutricionalProfileUI extends AbstractUI{

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

         
        try {
            NutricionalProfile newNutricionalProfile = this.theController.registerNutricionalProfile(this.theController.obtainCurrentCafeteriaUser(), dailyCalories, dailySalt, weeklyCalories, weeklySalt);
            System.out.println("Nutricional Profile Successfully Registered");
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterNutricionalProfileUI.class.getName()).log(Level.SEVERE, "Error Registering Nutricional Profile" , ex);
            return false;
        }        
    }

    @Override
    public String headline() {
        return "Regist Nutricional Profile";
    }
}
