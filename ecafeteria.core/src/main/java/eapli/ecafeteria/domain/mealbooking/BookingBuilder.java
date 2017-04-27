/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingBuilder {

    private Meal meal;

    /**
     * Cafeteria user.
     */
    private CafeteriaUser cafeteriaUser;

    /**
     * Adds the cafeteria user to the builder object.
     *
     * @param cafeteriaUser cafeteria user
     * @return the current object
     */
    public BookingBuilder withCafeteriaUser(CafeteriaUser cafeteriaUser) {
        this.cafeteriaUser = cafeteriaUser;
        return this;
    }

    /**
     * Adds the meal to the builder object.
     *
     * @param meal meal
     * @return the current object
     */
    public BookingBuilder withMeal(Meal meal) {
        this.meal = meal;
        return this;
    }

    /**
     * Creates the reservation object.
     *
     * @return the reservation created
     */
    public Booking build() {
        return new Booking(this.meal, this.cafeteriaUser);
    }

}
