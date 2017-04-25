/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.framework.presentation.console.AbstractUI;

/**
 * SRR-06 Classe do UI de escolha e booking de uma unica refeicao SRR-06
 * @author Hugo
 */
public class BookingUI extends AbstractUI{

    @Override
    protected boolean doShow() {
        
        System.out.println("Entramos no BookingUi");
        
        //INSERIR codigo do bookingUI aqui.
        
        
        System.out.println("Terminamos o BookingUi");
        
        return false;   //Nao identifiquei uso, pelo consegui constatar. Alguns retornam falso, outros true. O return true if this "scope" should end(HugoB)
    }

    @Override
    public String headline() {
        return "Booking ";
    }
    
}
