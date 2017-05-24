/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author Fernando
 */
public class RegisterMenuPlanAction implements Action{
    
	@Override
	public boolean execute() {
            return new RegisterMenuPlanUI().show();
	}
}
