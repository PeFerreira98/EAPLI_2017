/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.mealplans.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Nuno Filipe 1131106
 */
public class RegisterMealPlanController implements Controller {
    
    
        private final MealPlanRepository repository = PersistenceContext.repositories().mealPlans();

    public MealPlan registerMealPlan(Calendar dateStart, Calendar dateEnd)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final MealPlan newMealPlan = new MealPlan(dateStart, dateEnd);
        return this.repository.save(newMealPlan);
    }
    
    
}
