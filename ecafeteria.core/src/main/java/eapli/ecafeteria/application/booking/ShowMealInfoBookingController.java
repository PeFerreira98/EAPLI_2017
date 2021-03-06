/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import java.util.List;
import java.util.Observer;
import java.util.logging.Logger;

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
        if(meal == null) throw new IllegalArgumentException("Selected Meal was null");
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
        CafeteriaUser activeCafeteriaUser = null;
        try{
            Username username = Application.session().session().authenticatedUser().username();
            //TO DELETE:
            //CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(true);
            activeCafeteriaUser = new CafeteriaUserService().findCafeteriaUserByUsername(username);
        }
        catch(javax.persistence.PersistenceException ex){
            String error = "Error getting the CafeteriaUser of loged SystemUser.   " + ex;
            Logger.getGlobal().severe(error);
        }
        
        return activeCafeteriaUser;
    } 
   
    /**
     * Permite obter o consumo nutricional sob a forma de NutricionalInfo agendado para 
     * um utilizador para a semana de uma meal que pretende agendar. Inclui o consumo da
     * meal que indica.
     * @param meal a meal que se quer fazer booking.
     * @return um NutricionalInfo que é o somatorio de todas as meals agendadas. + esta.
     */
    public NutricionalInfo returnWeekInfo(Meal meal){
        // O proprio servico lida com nulls, visto o controlador nao saber como lidar.
        
        //Obter o cafeteriaUser
        CafeteriaUser activeCafeteriaUser = this.obtainCurrentCafeteriaUser();
        
        MealNutritionalConsumptionService mealNutritionalConsumptionService = new MealNutritionalConsumptionService();
        
        return mealNutritionalConsumptionService.planedWeekConsumption(activeCafeteriaUser, meal);
    }
    
}
