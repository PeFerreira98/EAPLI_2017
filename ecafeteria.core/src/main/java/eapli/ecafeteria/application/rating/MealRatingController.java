/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.rating;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Hugo & PEdro
 */
public class MealRatingController {
    
    /**
     * Returns a list with all meals this cafeteria User can rate.
     * @return 
     */
    public List<Meal> listMeals(){
        List<Meal> mealList = new ArrayList();
        
        Iterable <Meal> iterable = null;
        
        //iterable = new ListMealService().allMeals(); // Nao funciona porque está bloqueado por permisoes.
        //TODO substituir todas as meals por todas as meals referentes ao utilizador????
        
        Iterable<Meal> it = PersistenceContext.repositories().meals().findAll();
        for(Meal meal : it) mealList.add(meal);
        
        return mealList;
    }
    
    
    /**
     * Registers a new meal rating for the loged cafeteria user
     * @param meal meal to rate
     * @param rate rate of meal
     * @param comment comment , can be empty. ""
     * @return success of creating and registing the new rating.
     */
    public boolean registerNewMealRating(Meal meal, int rate,  String comment){
        return registerNewMealRating( this.getActiveCafeteriaUser() , meal, rate, comment);
    }
    
    /**
     * returns the loged in cafeteriaUser
     * @return the SystemUser cafeteriaUser
     */
    private CafeteriaUser getActiveCafeteriaUser(){
        CafeteriaUser cafeteriaUser = null;
        Username userName = Application.session().session().authenticatedUser().username();
        
        try{
            cafeteriaUser = new CafeteriaUserService().findCafeteriaUserByUsername(userName);
        }
        catch(javax.persistence.PersistenceException ex){
            String error = "Error getting the CafeteriaUser of loged SystemUser.   " + ex;
            Logger.getGlobal().severe(error);
            
        }
        
        return cafeteriaUser;
    }
    
    /**
     * Registers a new meal rating for a cafeteria user
     * @param cafeteriaUser cafeteriaUser doing the rating.
     * @param meal meal to rate
     * @param rate rate of meal
     * @param comment comment , can be empty. ""
     * @return success of creating and registing the new rating.
     */
    public boolean registerNewMealRating(CafeteriaUser cafeteriaUser , Meal meal, int rate, String comment){
        return new MealRatingService().registerNewMealRating(cafeteriaUser, meal, rate, comment);
    }
    
    
    
    
}
