/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
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
                mealsOfPublishedMenuFromCertainDate(date)) {
            listMealTypes.add(meal.mealType());
        }
        return listMealTypes;
    }

    public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
        Iterable<Menu> menu = new ListMenuService().findMenuByDate(date);

        if (!menu.iterator().hasNext()) {
            return null;
        }

        return new ListMealService().mealsOfMenuByDateMealType(date, mealType, menu.iterator().next());
    }

    public Booking bookingMeal(CafeteriaUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {

        /* Check if user has enough balance to reserve meal */
        if (user.hasSufficientBalance(meal.dish().currentPrice())) {
            /* Check if time limit not exceeded and if there are available meals
             * If positive add reservation to meal
             */
            if (meal.registerReservation()) {
                Booking reserve = new Booking(user, meal);
                PersistenceContext.repositories().reserves().save(reserve);
                //retirar o dinheiro
                user.registerExpense(meal.dish().currentPrice().amountAsDecimal());
                return reserve;
            } else {
                System.out.
                        println("Reservation time limit exceed or there are no available meals to reserve!");
            }
        } else {
            System.out.println("Your current balance is not enough!");
        }
        
        return null;
    }
}
