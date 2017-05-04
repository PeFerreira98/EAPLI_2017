/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

    Iterable<Booking> findNextReserves(Calendar dateInitial, Calendar dateFinal, CafeteriaUser user);
    
}
