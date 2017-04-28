/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.meals.ListMenuService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingController implements Controller {

    public Iterable<MealType> listMealType(Calendar date) {
        Set<MealType> listMealTypes = new HashSet();
        for (Meal meal : PersistenceContext.repositories().meals().
                mealsOfCertainDate(date)) {
            listMealTypes.add(meal.mealType());
        }
        return listMealTypes;
    }

    public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
        Iterable<Menu> menu = new ListMenuService().findMenuByDate(date);

        if (!menu.iterator().hasNext()) {
            return null;
        }

        /**
         * TO DO
         */
        return null;
    }

    public Booking bookingMeal(SystemUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        return null;
    }
}
