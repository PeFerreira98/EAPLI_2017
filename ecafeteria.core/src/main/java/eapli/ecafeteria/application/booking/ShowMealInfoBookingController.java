/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import java.util.List;

/**
 *
 * @author Pedro Pereira
 */
public class ShowMealInfoBookingController {
    
    /**
     * Devolve a informacao nutricional de uma meal.
     * @param meal meal a obter a informacao nutricional
     * @return a informacao nutricional da meal
     */
    public NutricionalInfo obtainNutricionalInfo(Meal meal){
        Dish dish = meal.dish();
        NutricionalInfo mealNutricionalInfo = dish.nutricionalInfo();
        return mealNutricionalInfo;   
    }
    
//    public List<DishAllergen> obtainListAllergen(Meal meal){
//        Dish dish = meal.dish();
//        List<DishAllergen> dishAllergenList = dish.dishAllergens();
//        
//        return dishAllergenList;
//    }
    
    /**
     * Verifica (se possivel) se o user logado e alegico a uma meal
     * @param meal meal a verificar.
     * @return se e alergico. Se imposivel determinar retorna falso.
     */
    public boolean isAllergic(Meal meal){
        if(meal == null) return false;
        AllergyDetectionService svc = new AllergyDetectionService();
        
        CafeteriaUser activeCafeteriaUser = this.obtainCurrentCafeteriaUser();
        if(activeCafeteriaUser == null) return false;
        
        svc.isCafeteriaUserAllergicToMeal(activeCafeteriaUser, meal);
        
        //TODO
        System.out.println("Not implemented yet");
        return false;
    }
    
    /**
     * Obtem o cafeteria user que esta actualmente logado. Se nao existir, devolve null.
     * @return o cafeteria user que esta actualmente logado. Se nao existir, devolve null.
     */
    public CafeteriaUser obtainCurrentCafeteriaUser(){
        CafeteriaUser activeCafeteriaUser;
//      Estamos a pensar implementar um servico para isto.        
//      CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(true);
//      CafeteriaUser cafeteriaUser = cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
//        
//        return activeCafeteriaUser;
        //TODO
        System.out.println("Not implemented yet");
        return null;
    } 
   
    
    
}
