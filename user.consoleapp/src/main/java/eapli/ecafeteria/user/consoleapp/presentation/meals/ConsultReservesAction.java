/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class ConsultReservesAction implements Action{
    
    @Override
	public boolean execute() {
		return new ConsultReservesUI().show();
	}
    
}
