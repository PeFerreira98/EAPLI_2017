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

    public MealPlan(Calendar dateStart, Calendar dateEnd) {

        if (dateEnd.after(dateStart)) {
            this.periodStart = dateStart;
            this.periodEnd = dateEnd;
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

    public Calendar getPeriodStart() {
        return periodStart;
    }

    public Calendar getPeriodEnd() {
        return periodEnd;
    }
}
