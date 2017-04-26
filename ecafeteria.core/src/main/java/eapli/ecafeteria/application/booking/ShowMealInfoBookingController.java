/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import java.util.List;

/**
 *
 * @author Pedro Pereira
 */
public class ShowMealInfoBookingController {
    
    
    public void obtainNutricionalInfo(Meal meal){
        Dish dish = meal.dish();
        NutricionalInfo nutricionalInfo = dish.nutricionalInfo();
    }
    
//    public List<DishAllergen> obtainListAllergen(Meal meal){
//        Dish dish = meal.dish();
//        List<DishAllergen> dishAllergenList = dish.dishAllergens();
//        
//        return dishAllergenList;
//    }
    
    public boolean isAllergic(Meal meal){
        AllergyDetectionService svc = new AllergyDetectionService();
        System.out.println("Not implemented yet");
        return false;
    }
}
