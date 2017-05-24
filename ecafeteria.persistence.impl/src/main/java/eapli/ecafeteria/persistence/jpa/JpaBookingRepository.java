/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.mealbooking.BookingState;
import eapli.ecafeteria.persistence.BookingRepository;

import java.awt.print.Book;
import java.util.Calendar;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long> implements BookingRepository {

    @Override
    public Iterable<Booking> findNextReserves(Calendar dateInitial, Calendar dateFinal, CafeteriaUser user) {

        Query createQuery = entityManager().createQuery("SELECT r FROM Booking r WHERE r.cafeteriaUser=:user AND r.meal.date BETWEEN :dateInitial AND :dateFinal");
        createQuery.setParameter("user", user);
        createQuery.setParameter("dateInitial", dateInitial, TemporalType.DATE);
        createQuery.setParameter("dateFinal", dateFinal, TemporalType.DATE);
        
        return createQuery.getResultList();
    }

	@Override
	public Iterable<Booking> listPayedUserBookings(CafeteriaUser user) {
		
		Query createQuery = entityManager().createQuery("SELECT r FROM Booking r WHERE r.cafeteriaUser=:user AND r.state=:state");
        createQuery.setParameter("user", user);
        createQuery.setParameter("state", BookingState.BOOKED);
        
        return createQuery.getResultList();
	}

}
