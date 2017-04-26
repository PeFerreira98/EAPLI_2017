/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.meals.Meal;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingBuilder {
    
    private Meal meal;
    private SystemUser systemUser;
    
    public BookingBuilder withMeal(Meal meal){
        this.meal = meal;
        return this;
    }
    
    public BookingBuilder withCafeteriaUser(SystemUser systemUser){
        this.systemUser = systemUser;
        return this;
    }
    
    public Booking build() {
        return new Booking(this.meal, null);
    }
    
}
