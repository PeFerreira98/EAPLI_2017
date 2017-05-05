/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.ecafeteria.persistence.MealBatchRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Pedro Tedim
 */
public class RegisterMealBatchController implements Controller {
    
    private final MealBatchRepository repository = PersistenceContext.repositories().mealBatches();
    
     public MealBatch registerMealBatch(Calendar mealDate, String matRef, String batchCode)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final MealBatch mbat = new MealBatch(mealDate, matRef, batchCode);
        return this.repository.save(mbat);
    }
    
}
