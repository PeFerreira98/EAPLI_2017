/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class OpenCashRegisterController {
    
    public Iterable<MealType> getMealTypes() {
        return PersistenceContext.repositories().mealTypes().findAll();
    }
    
    public MealType mealByDefault() {
        
        final MealTypeRepository mealRepository = PersistenceContext.repositories().mealTypes();

        return mealRepository.findByDefault();
    }
    
    public boolean open(String number, MealType mealType, Calendar date){
        
        return false;
    }

    
}
