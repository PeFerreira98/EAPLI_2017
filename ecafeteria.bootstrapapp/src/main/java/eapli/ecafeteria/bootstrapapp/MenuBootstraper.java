/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.meals.CreateMenuController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 *
 * @author zero_
 */
public class MenuBootstraper implements Action {

    @Override
    public boolean execute() {

        Calendar clBeginning = GregorianCalendar.getInstance();
        Calendar clEnding = GregorianCalendar.getInstance();
        clBeginning.set(2017, Calendar.APRIL, 9);
        clEnding.set(2017, Calendar.APRIL, 15);

        register("MenuSemanaAbril", clBeginning, clEnding);
        return false;
    }

    private void register(String name, Calendar beginning, Calendar ending) {
        final CreateMenuController controller = new CreateMenuController();
        try {
            controller.createMenu(name, beginning, ending);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(ECafeteriaBootstrap.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
