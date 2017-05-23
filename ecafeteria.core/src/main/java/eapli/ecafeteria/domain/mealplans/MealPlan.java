/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealplans;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MealPlan other = (MealPlan) obj;
        if (!Objects.equals(this.periodStart, other.periodStart)) {
            return false;
        }
        if (!Objects.equals(this.periodEnd, other.periodEnd)) {
            return false;
        }
        return true;
    }
    
    
}
