/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
@Entity
public class Booking implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Meal meal;

    @ManyToOne
    private CafeteriaUser cafeteriaUser;
    private String description;

    protected Booking() {

    }

    public Booking(Meal meal, CafeteriaUser cafeteriaUser) {
        this.meal = meal;
        this.cafeteriaUser = cafeteriaUser;
    }

    public Calendar mealDate() {
        return this.meal.date();
    }

    public Meal meal() {
        return this.meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }

        final Booking other = (Booking) o;
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        if (!Objects.equals(this.cafeteriaUser, other.cafeteriaUser)) {
            return false;
        }

        return true;
    }

    public boolean equals(Booking booking) {
        if (this == booking) {
            return true;
        }
        if (!(booking instanceof Booking)) {
            return false;
        }

        final Booking other = (Booking) booking;
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        if (!Objects.equals(this.cafeteriaUser, other.cafeteriaUser)) {
            return false;
        }

        return true;
    }

    public String description() {
        return this.description;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
