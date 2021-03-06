/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public interface CashRegisterRepository extends DataRepository<CashRegister, Long> {

    /**
     * This method find the cash register by is numbeer
     *
     * @param number cash register number
     * @return cash register
     */
    public CashRegister findByNumber(String number);

}
