/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealplans;

import java.io.Serializable;

/**
 *
 * @author Nuno Costa 1131106 - Alexandra Ferreira 1140388
 */
public class MealPlanItem implements Serializable {

    private int qtdPlanned;
    private int qtyReserved;

    protected MealPlanItem() {
    }

    /**
     * Constructor
     *
     * @param qtdPlanned
     */
    public MealPlanItem(int qtdPlanned) {
        this.qtdPlanned = qtdPlanned;
        this.qtyReserved = 0;
    }

    /**
     * Constructor
     *
     * @param qtdPlanned
     * @param qtyReserved
     */
    public MealPlanItem(int qtdPlanned, int qtyReserved) {
        this.qtdPlanned = qtdPlanned;
        this.qtyReserved = qtyReserved;
    }

    /**
     *
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

    /**
     *
     * @return total of meals reserved.
     */
    public boolean addReserve() {
        this.qtyReserved++;
        return true;
    }

    /**
     * consult qtyReserved of reservations made.
     *
     * @return
     */
    public int quantityReserved() {
        return this.qtyReserved;
    }

}
