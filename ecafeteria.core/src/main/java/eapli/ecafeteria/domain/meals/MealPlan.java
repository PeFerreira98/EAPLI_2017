/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.util.Date;

/**
 *
 * @author Nuno Costa 1131106 - Alexandra Ferreira 1140388
 */
public class MealPlan {

    private Date periodStart;
    private Date periodEnd;

    public MealPlan(Date dateStart, Date dateEnd) {

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
    public void changePeriod(Date start, Date end) {
        this.periodStart = start;
        this.periodEnd = end;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

}
