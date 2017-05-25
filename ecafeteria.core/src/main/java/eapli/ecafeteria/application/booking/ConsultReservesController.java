/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class ConsultReservesController {

    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().
            cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());

    /**
     * Method that give the reserves between current date and the end date
     *
     * @param user
     * @param dateInitial variable representing the current date.
     * @param dateFinal variable that represents the end date.
     * @return reserves.
     */
    public Iterable<Booking> getReservesBetweenDates(CafeteriaUser user, Calendar dateInitial,
            Calendar dateFinal) {

        if (DateTime.isPreviousDate(dateFinal, dateInitial)) {
            throw new IllegalArgumentException();
        }

        Iterable<Booking> reserves = PersistenceContext.repositories().
                reserves().findNextReserves(dateInitial, dateFinal, user);

        if (reserves == null) {
            throw new NoSuchElementException("User not found");
        }

        return reserves;
    }

    public CafeteriaUser returnActiveCafeteriaUser() {
        return this.cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
    }
}
