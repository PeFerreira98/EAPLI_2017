/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.sales;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class LoadBalanceController {
    
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
    
    public CafeteriaUser getCafeteriaUser(String username) {
        
        try{
            return cafeteriaUserRepository.findByUsername(new Username(username));
        }catch(Exception e){
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Loads the balance of the cafeteria user's account.
     * @param user Cafeteria User
     * @param amount Ammount to be added to the account
     * @return True, if the account's balance is successfully loaded. False, if not.
     * @throws DataConcurrencyException 
     */
    public boolean loadAccount(CafeteriaUser user, BigDecimal amount) throws DataConcurrencyException, DataIntegrityViolationException {
        if (user.registerLoad(amount)) {
            
            cafeteriaUserRepository.save(user);
            
            return true;
        } 
        return false;
    }
}
