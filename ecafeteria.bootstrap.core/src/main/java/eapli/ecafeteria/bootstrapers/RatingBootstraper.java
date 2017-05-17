/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.application.rating.MealRatingController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Pereira
 */
public class RatingBootstraper implements Action {

    @Override
    public boolean execute() {
        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        final MealRepository mealRepository = PersistenceContext.repositories().meals();
        Meal meal = null;
                
        Iterable<Meal> itMeal = mealRepository.findAll();
        
        for(Meal m : itMeal){
            meal = m;
            if(meal != null){
                break;
            }
        }
        //this.getActiveCafeteriaUser()
        //Menu menu = menuRepository.findByName("MenuSemanaAbril");
        //Meal mealTest = mealRepo.mealsByMenu(menu);
          CafeteriaUserService service = new CafeteriaUserService();
          CafeteriaUser cafeteriaUser = service.findCafeteriaUserByUsername(new Username("900330"));
          
          register(cafeteriaUser, meal, 2, "Muito salgado");
          
          int expected = 1;
          int found = 0;
          RatingRepository ratingRepo = PersistenceContext.repositories().ratings();
          
          Iterable<Rating> itRating = ratingRepo.findAll();
          for(Rating r : itRating){
              if(r != null){
                  found++;
              }
          }
          System.out.println("Ratings found in database: " + found + " / "+ expected);
          
        return false;
    }
    
    private void register(CafeteriaUser cafeteriaUser , Meal meal, int rate, String comment){
        final MealRatingController controller = new MealRatingController();
        try {
            controller.registerNewMealRating(cafeteriaUser, meal, rate, comment);
        }  catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
	    // due to the tentative of inserting a duplicated user
	    Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
		    .info("EAPLI-DI001: bootstrapping existing record");
        }
        
    }
    
}
