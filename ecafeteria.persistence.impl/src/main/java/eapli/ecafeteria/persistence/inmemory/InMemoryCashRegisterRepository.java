/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class InMemoryCashRegisterRepository extends InMemoryRepositoryWithLongPK<CashRegister> implements CashRegisterRepository{
    
}
