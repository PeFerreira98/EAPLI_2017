/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingController implements Controller {

    public Iterable<Meal> listMeals(Calendar date) {
        //Find a way to search meals by date...
        //return mealsByDate(date);
        return null;
    }

    public Iterable<Meal> listAllMeals() {
        //return allMeals();
        return null;
    }

    public Iterable<Menu> listAllMenu() {
        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        return menuRepository.findAll();
    }

    public Iterable<Meal> listAllMealsFromMenu(Menu menu) {
       //final Iterable<Meal> allMeals = menu.menuMeals();
       //return allMeals;
       return null;
    }

    public Booking bookingMeal(Meal meal) throws DataIntegrityViolationException {
        return null;
    }
}
