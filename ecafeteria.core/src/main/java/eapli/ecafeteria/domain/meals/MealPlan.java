/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.util.Calendar;

/**
 *
 * @author Nuno Costa 1131106 - Alexandra Ferreira 1140388
 */
public class MealPlan {

    private Calendar periodStart;
    private Calendar periodEnd;
    private int maxDishes;

    public MealPlan(Calendar dateStart, Calendar dateEnd, int maxDishes) {

        if (dateEnd.after(dateStart)) {
            this.periodStart = dateStart;
            this.periodEnd = dateEnd;
            this.maxDishes = maxDishes;
        } else {
            System.out.println("Dates Invalid!!");
        }
    }

    /**
     * Constructor
     *
     * @param start
     * @param end
     */
    public void changePeriod(Calendar start, Calendar end) {
        this.periodStart = start;
        this.periodEnd = end;
    }

    public void changeMaxDishes(int dishes) {
        this.maxDishes = dishes;
    }

    public Calendar getPeriodStart() {
        return periodStart;
    }

    public Calendar getPeriodEnd() {
        return periodEnd;
    }

    public int getMaxDishes() {
        return maxDishes;
    }

}
