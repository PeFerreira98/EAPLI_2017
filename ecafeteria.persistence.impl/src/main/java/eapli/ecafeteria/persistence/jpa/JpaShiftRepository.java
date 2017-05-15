/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.domain.cashregister.Shift;
import eapli.ecafeteria.persistence.ShiftRepository;
import javax.persistence.Query;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class JpaShiftRepository extends CafeteriaJpaRepositoryBase<Shift, Long> implements ShiftRepository {

    @Override
    public Shift findByOpenedCashRegister(CashRegister cashRegister) {

        final Query q = entityManager().
                createQuery("select s from Shift s where s.cashRegister=:cashRegister", Shift.class);
        q.setParameter("cashRegister", cashRegister);

        return (Shift) q.getSingleResult();
    }

}
