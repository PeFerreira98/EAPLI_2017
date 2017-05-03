/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;

/**
 *
 * @author Nuno Costa 1131106 - Alexandra Ferreira 1140388 
 */
public class MealPlanItem implements Serializable {

    private int qtdPlanned;

    protected MealPlanItem() {
    }

    /**
     * Constructor
     *
     * @param qtdPlanned
     */
    public MealPlanItem(int qtdPlanned) {
        this.qtdPlanned = qtdPlanned;
    }

    /**
     *
     * @param number
     * @return total of meals reserved.
     */
    public boolean addPlanned() {
        this.qtdPlanned++;
        return true;
    }

    /**
     * consult qtdPlanned of meals planed
     *
     * @return return qtdPlanned of meals.
     */
    public int quantityPlanned() {
        return this.qtdPlanned;
    }

    /**
     * Constructor
     *
     * @param quantity
     */
    public void changePlaned(int quantity) {
        this.qtdPlanned = quantity;
    }

}
