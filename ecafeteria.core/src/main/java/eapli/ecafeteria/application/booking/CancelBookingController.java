/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class CancelBookingController implements Controller{

	private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
	private final BookingRepository bookingRepository = PersistenceContext.repositories().reserves();

	public Booking cancelBooking(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {

		if (booking == null) {
			throw new IllegalArgumentException();
		}

		//FIXME : refund not working
//		if (refund(booking)) {
			
			if (booking.cancelBooking()) {
				
				return this.bookingRepository.save(booking);
			}
//		}

		return null;
	}

	private boolean refund(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
		
		final int limitHourForReservation = booking.meal().mealType().limitForReservation();
		final int currentHour = DateTime.now().getTime().getHours();
		final Money refund = booking.meal().dish().currentPrice();

		if (currentHour > limitHourForReservation) {
			refund.subtract(new Money(refund.amount() / 2.0, null));
		}

		booking.user().accountBalance().add(refund);

		// TODO: Add validation and fix this
		return true;
	}

	public Iterable<Booking> listPayedUserBookings(CafeteriaUser user) {
		return this.bookingRepository.listPayedUserBookings(user);
	}
	
	public CafeteriaUser returnActiveCafeteriaUser() {
		return this.cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
	}
}
