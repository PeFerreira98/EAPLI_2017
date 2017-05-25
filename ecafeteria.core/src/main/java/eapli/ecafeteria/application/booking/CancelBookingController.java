/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.alert.BookingAlert;
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
import java.util.Observable;

/**
 *
 * @author Marcos
 */
public class CancelBookingController extends Observable implements Controller {

    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
    private final BookingRepository bookingRepository = PersistenceContext.repositories().reserves();

    public Booking cancelBooking(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        addObservers();

        if (booking == null) {
            throw new IllegalArgumentException();
        }

        //FIXME : refund not working
//		if (refund(booking)) {
        if (booking.cancelBooking()) {

            Booking book = this.bookingRepository.save(booking);

            this.notifyObservers(book);
            return book;
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
        return new CafeteriaUserService().obtainCurrentCafeteriaUser();
    }

    private void addObservers() {
        for (BookingAlert ba : PersistenceContext.repositories().bookingAlerts().findAll()) {
            this.addObserver(ba);
        }
    }

}
