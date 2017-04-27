/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Pedro Pereira
 */
public class AllergyDetectionService {
    
    public boolean isAllergic(Meal meal){
        CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(true);
        CafeteriaUser cafeteriaUser = cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
//        
//        //List<DishAllergen> dishAllergenList = dish.dishAllergens();
        System.out.println("FALTA IMPLEMENTAR ALLERGYDETECTIONSERVICE");
        return false;
    }
    
    
}
