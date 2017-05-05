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
import java.util.List;

/**
 *
 * @author Pedro Tedim
 */
public class ListMealByBatchCodeController implements Controller{
    
    private final MealBatchRepository repository = PersistenceContext.repositories().mealBatches();
    
     public List<MealBatch> searchMealByBatchCode(String batchCode)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.repository.findOne(batchCode);
    }
     
     public Iterable<MealBatch> all() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return this.repository.findAll();
    }
}
