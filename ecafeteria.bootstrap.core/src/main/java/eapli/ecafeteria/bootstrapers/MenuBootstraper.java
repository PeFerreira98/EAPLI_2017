/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menus.RegisterMenuController;
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

        clBeginning.set(2017, Calendar.MAY, 22);
        clEnding.set(2017, Calendar.MAY, 28);

        register("MenuSemanaMaio", clBeginning, clEnding);
        
        return false;
    }

    private void register(String name, Calendar beginning, Calendar ending) {
        final RegisterMenuController controller = new RegisterMenuController();
        try {
            controller.createMenu(name, beginning, ending);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
