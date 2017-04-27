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
import java.util.Observable;

/**
 * Servico cuja responsabilidade e detectar alergias de users com Meals. OBSERVABLE
 * @author Pedro Pereira
 */
public class AllergyDetectionService extends Observable{
    
    /**
     * Determina se um determinado user é alergico a uma meal. OBSERVABLE
     * @param cafeteriaUser cafeteria user a vereficar.
     * @param meal meal a vereficar
     * @return true de alergico, false se não ou impossivel comparar.
     */
    public boolean isCafeteriaUserAllergicToMeal(CafeteriaUser cafeteriaUser, Meal meal){
        if(cafeteriaUser == null || meal == null) return false;
        boolean wasAllergyDetected = false;

        
//        // TODO
//        //List<DishAllergen> dishAllergenList = dish.dishAllergens();
        System.out.println("FALTA IMPLEMENTAR ALLERGYDETECTIONSERVICE");
        
        
        if(wasAllergyDetected){             //Se detectamos uma alergia,
            this.setChanged();              // Vamos informar que estamos alterados
            this.notifyObservers();         // Se estamos alterados, vai informar todos os observadores.
        }
        
        return wasAllergyDetected;
    }
    
    
}
