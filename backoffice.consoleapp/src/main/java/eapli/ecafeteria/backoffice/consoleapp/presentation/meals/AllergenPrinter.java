/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author KAMMIKAZI
 */
public class AllergenPrinter implements Visitor<Allergen>{

    @Override
    public void visit(Allergen visitee) {
	System.out.printf("%-30s%-25s%-10s%-4s\n", visitee.description(), visitee.getName(),
		visitee.id(), String.valueOf(visitee.isActive()));
    }
}
