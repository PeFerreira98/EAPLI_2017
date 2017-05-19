/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Nuno Filipe 1131106
 */
public class CashRegisterBootstraper implements Action {

    @Override
    public boolean execute() {
        try {
            CashRegister cash0 = new CashRegister("0");

            final CashRegisterRepository cashRegisterRepository
                    = PersistenceContext.repositories().cashRegisters();

            cashRegisterRepository.save(cash0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
