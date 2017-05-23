package eapli.ecafeteria.bootstrapers;

import java.util.Iterator;
import java.util.logging.Logger;

import eapli.ecafeteria.application.booking.BookingController;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class BookingBootstraper implements Action {

	@Override
	public boolean execute() {

		final MealRepository mealRepository = PersistenceContext.repositories().meals();
		final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
		
		CafeteriaUser cafeteriaUser = cafeteriaUserRepository.findAll().iterator().next();
		final Iterator<Meal> meals = mealRepository.findAll().iterator();

		register(cafeteriaUser, meals.next());
		register(cafeteriaUser, meals.next());
		
		return false;
	}
	
	private void register(CafeteriaUser cafeteriaUser, Meal meal){
		final BookingController bookingController = new BookingController();
		try {
			bookingController.bookingMeal(cafeteriaUser, meal);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
	}

}
