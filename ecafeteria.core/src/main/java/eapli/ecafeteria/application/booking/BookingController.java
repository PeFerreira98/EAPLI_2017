/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.application.meals.ListMealTypeService;
import eapli.ecafeteria.domain.alert.BookingAlert;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Observable;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingController extends Observable implements Controller {

    private final ListMealTypeService mealTypeSvc = new ListMealTypeService();
    private final ListMealService mealSvc = new ListMealService();

    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
    private final BookingRepository bookingRepository = PersistenceContext.repositories().reserves();

    public Booking bookingMeal(CafeteriaUser cafeteriaUser, Meal meal)
            throws DataConcurrencyException, DataIntegrityViolationException {

        addObservers();

        Booking booking = new Booking(cafeteriaUser, meal);
        Booking book = this.bookingRepository.save(booking);
        
        this.notifyObservers(book);

        //return booking;

        //FIXME: Money verifications still not working! (Please TEST before commit!)

		//Check if user has enough balance to reserve meal
		if (cafeteriaUser.hasSufficientBalance(meal.dish().currentPrice())) {
			
			//FIXME: add mealplan verifications the right way (mealplan service, repository, etc.)
			//Check if time limit not exceeded and if there are available meals
			//If positive add reservation to meal
			if (meal.registerReservation()) {
				
				this.bookingRepository.save(booking);
				
				// retirar o dinheiro
				cafeteriaUser.registerExpense(meal.dish().currentPrice().amountAsDecimal());
				return booking;
			} else {
				System.out.println("Reservation time limit exceed or there are no available meals to reserve!");
			}
		} else {
			System.out.println("Your current balance is not enough!");
		}
		
		return null;
         
    }

    public CafeteriaUser returnActiveCafeteriaUser() {
        return this.cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
    }

    public Iterable<MealType> listMealTypes() {
        return this.mealTypeSvc.activeMealTypes();
    }

    public Iterable<Meal> listMealsByDateAndMealType(Calendar date, MealType mealType) {
        return this.mealSvc.mealsByDateAndMealType(date, mealType);
    }

    public Iterable<Meal> getMeals() {
        return PersistenceContext.repositories().meals().findAll();
    }

    private void addObservers() {
        for (BookingAlert ba : PersistenceContext.repositories().bookingAlerts().findAll()) {
            this.addObserver(ba);
        }
    }
}
