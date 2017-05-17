/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.rating;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.rating.Comment;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/** Serviço de lida com operacoes relacionadas com Ratings.
 *
 * @author Hugo & Pedro
 */
public class MealRatingService {
    
    private RatingRepository ratingRepository = PersistenceContext.repositories().ratings();
    
    /**
     * Registers a new meal rating for a cafeteria user
     * @param cafeteriaUser cafeteriaUser doing the rating.
     * @param meal meal to rate
     * @param rate rate of meal
     * @param comment comment , can be empty. ""
     * @return success of creating and registing the new rating.
     */
    public boolean registerNewMealRating(CafeteriaUser cafeteriaUser , Meal meal, int rate, String comment) throws DataConcurrencyException, DataIntegrityViolationException{
        if (cafeteriaUser == null) throw new IllegalArgumentException("cafeteriaUser == null");
        if (meal == null) throw new IllegalArgumentException("meal == null");
        
        
        //TODO criar o objecto Rating, Comentario e codifica-lo. e decomentar acima.
        // System.out.println("Falta Implementar o codigo de criar um novo rating. Hugo & Pedro");
        Comment c = new Comment(comment, cafeteriaUser);
        Rating newRating = new Rating(rate, cafeteriaUser, meal, c);
        
        this.ratingRepository.save(newRating);
        
        return true;
    }
    
    
}
