/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterMealPlanController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Filipe 1131106
 */
public class MealPlanBootstraper implements Action {

    @Override
    public boolean execute() {

        Calendar date1 = new GregorianCalendar(2017, 5, 25);
        Calendar date2 = new GregorianCalendar(2017, 5, 28);

        register(date1, date2);
        return false;
    }

    private void register(Calendar dateStart, Calendar dateEnd) {
        final RegisterMealPlanController controller = new RegisterMealPlanController();
        try {
            controller.registerMealPlan(dateStart, dateEnd);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
