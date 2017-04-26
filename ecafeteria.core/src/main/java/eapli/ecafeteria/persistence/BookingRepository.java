/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.framework.domain.Repository;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public interface BookingRepository extends Repository<Booking, Long>{
    
    public Iterable<Booking> listBookingsByUser(SystemUser systemUser);
    
}
