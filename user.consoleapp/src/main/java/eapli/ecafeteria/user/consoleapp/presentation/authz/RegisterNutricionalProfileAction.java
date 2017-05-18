/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.authz;

import eapli.framework.actions.Action;

/**
 *
 * @author Tiago Silvestre
 */
public class RegisterNutricionalProfileAction implements Action {

    @Override
    public boolean execute() {
        return (new RegisterNutricionalProfileUI()).show();
    }

}
