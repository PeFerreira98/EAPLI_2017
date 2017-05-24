/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.mealbooking.BookingState;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class InMemoryBookingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingRepository {

    @Override
    public Iterable<Booking> findNextReserves(Calendar dateInitial, Calendar dateFinal, CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Iterable<Booking> listPayedUserBookings(CafeteriaUser user) {
		return match(e -> e.user().equals(user) && e.state().equals(BookingState.BOOKED));
	}

}
