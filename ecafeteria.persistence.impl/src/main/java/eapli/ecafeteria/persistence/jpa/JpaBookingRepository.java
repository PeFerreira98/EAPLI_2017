/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.Optional;

/**
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class JpaBookingRepository implements BookingRepository {

    @Override
    public Iterable<Booking> listBookingsByUser(SystemUser systemUser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Booking> first(int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Booking> page(int pageNumber, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Booking> read(Long identity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Booking save(Booking entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Booking entity) throws DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Booking update(Booking entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Long identity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Booking> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
