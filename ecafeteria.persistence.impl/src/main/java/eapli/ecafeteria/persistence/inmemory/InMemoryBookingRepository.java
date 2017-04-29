/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class InMemoryBookingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingRepository {

}
