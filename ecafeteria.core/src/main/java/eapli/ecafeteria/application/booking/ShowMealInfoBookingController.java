/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;
import java.util.Observer;

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
    
    /**
     * Devolve todos os alergenicos de uma meal.
     * @param meal meal a obter os allergenicos
     * @return List com todos os alergenicos de uma meal
     */
    public List<Allergen> obtainListAllergen(Meal meal){
        
        return new AllergyDetectionService().listAllergen(meal);
    }
    
    /**
     * Verifica (se possivel) se o user logado e alegico a uma meal
     * @param meal meal a verificar.
     * @return se e alergico. Se imposivel determinar retorna falso.
     */
    public boolean isAllergic( Meal meal, Observer ui){
        if(meal == null) return false;
        
        //Obter o cafeteriaUser
        CafeteriaUser activeCafeteriaUser = this.obtainCurrentCafeteriaUser();
        if(activeCafeteriaUser == null) return false;
        
        
        AllergyDetectionService allergyDetectionService = new AllergyDetectionService();    //Criar servico
        if (ui != null) allergyDetectionService.addObserver(ui);                                            //Adicionar observador
        
        boolean isAllergic = allergyDetectionService.isCafeteriaUserAllergicToMeal(activeCafeteriaUser, meal);
        
        if (ui != null) allergyDetectionService.deleteObserver(ui);                                         //Remover Observador
        
        return isAllergic;
    }
    
    /**
     * Obtem o cafeteria user que esta actualmente logado. Se nao existir, devolve null.
     * @return o cafeteria user que esta actualmente logado. Se nao existir, devolve null.
     */
    public CafeteriaUser obtainCurrentCafeteriaUser(){
        CafeteriaUser activeCafeteriaUser;

        //  Melhorias: implementar um servico para isto.        
        CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(true);
        CafeteriaUser cafeteriaUser = cafeteriaUserRepository.findByUsername(Application.session().session().authenticatedUser().username());
        
        return cafeteriaUser;
    } 
   
    
    
}
