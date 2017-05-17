/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Pereira
 */
public class NutricionalInfoPrinter implements Visitor<NutricionalInfo> {

    @Override
    public void visit(NutricionalInfo visitee) {
        int calories = visitee.calories();
        int salt = visitee.salt();
        System.out.print("Calorias: " + calories + " cal  ");
        System.out.println("Sal: "+ salt + " g");
        
       // System.out.printf("%-30s%-30s\n", visitee.calories(), visitee.salt());
    }

    @Override
    public void beforeVisiting(NutricionalInfo visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(NutricionalInfo visitee) {
        // nothing to do
    }

    
}
