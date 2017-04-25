/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 * SRR-06 Classe da accao de carregar na opcao de book a single Meal
 * @author Hugo
 */
public class BookingAction implements Action{

    @Override
    public boolean execute() {
        return (new BookingUI()).show();
    }
    
}
