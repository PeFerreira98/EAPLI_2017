/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.domain.cashregister.Shift;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public interface ShiftRepository extends DataRepository<Shift, Long> {

    /**
     * This method returns a new shift that is associated to a specific opened
     * cash register
     *
     * @param cashRegister
     * @return Shift
     */
    public Shift findByOpenedCashRegister(CashRegister cashRegister);
}
