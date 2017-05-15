/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.domain.cashregister.Shift;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class InMemoryShiftRepository extends InMemoryRepositoryWithLongPK<Shift> implements ShiftRepository{

    @Override
    public Shift findByOpenedCashRegister(CashRegister cashRegister) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
