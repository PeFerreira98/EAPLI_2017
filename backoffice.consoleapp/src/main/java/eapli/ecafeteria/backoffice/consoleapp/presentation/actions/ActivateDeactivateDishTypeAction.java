package eapli.ecafeteria.backoffice.consoleapp.presentation.actions;

import eapli.ecafeteria.backoffice.consoleapp.presentation.ui.ActivateDeactivateDishTypeUI;
import eapli.framework.actions.Action;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeAction implements Action {
    @Override
    public boolean execute() {
        return new ActivateDeactivateDishTypeUI().show();
    }
}