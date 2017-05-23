/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import javax.persistence.Query;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class JpaCashRegisterRepository extends CafeteriaJpaRepositoryBase<CashRegister, Long> implements CashRegisterRepository {

    @Override
    public CashRegister findByNumber(String number) {
        final Query q = entityManager().
                createQuery("select cr from CashRegister cr where cr.number=:number", CashRegister.class);
        q.setParameter("number", number);
        return (CashRegister) q.getResultList().iterator().next();
    }

}
