/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.rating;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.rating.Comment;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;

/** Serviço de lida com operacoes relacionadas com Ratings.
 *
 * @author Hugo & Pedro
 */
public class MealRatingService {
    
    private RatingRepository ratingRepository = PersistenceContext.repositories().ratings();
    private CommentRepository commentRepository = PersistenceContext.repositories().comments();
    
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
        Comment c = null;
        
        if(comment == null || comment.isEmpty()){
            comment = "No comment";
        }
        
        c = new Comment(comment, cafeteriaUser);
        this.commentRepository.save(c);
        
        Rating newRating = new Rating(rate, cafeteriaUser, meal, c);
        
        this.ratingRepository.save(newRating);
        
        return true;
    }
    
    /**
     * Devolve as meals que um cafeteriaUser pode fazer rating
     * @param cafeteriaUser o cafeteriaUser em questao
     * @return uma lista das meals que um cafeteriaUser pode fazer rating
     */
    public List<Meal> userRatableMeals(CafeteriaUser cafeteriaUser){
        List<Meal> mealList = new ArrayList();
        if (cafeteriaUser == null) return mealList;
        
        //Obtencao das bookings do utilizador.
        Iterable<Booking> allBookings = PersistenceContext.repositories().reserves().findAll();
        
        // Obtencao das meals ja com rating do utilizador.
        List<Meal> userRatedMeals = userRatedMeals(cafeteriaUser);
                
        for(Booking b : allBookings){
            if(
                b.user().equals(cafeteriaUser) &&  // Se o utilizador for o nosso.
                !userRatedMeals.contains(b.meal()) // E a meal ainda nao tiver sido rated pelo user
                // && b.isNoEstado que deve estar para poder ser comentado, Não existindo estados no booking ignoramos isto.  
              ){
                mealList.add(b.meal());             // entao adicionamos a lista.
            }
        }
        
        return mealList;
    }
    
    
    /**
     * Devolve uma lista com as meals que ja foram feitas ratings do user.
     * @param cafeteriaUser cafeteriaUser a inquirir
     * @return lista com as meals que ja foram feitas ratings do user.
     */
    public List<Meal> userRatedMeals(CafeteriaUser cafeteriaUser){
        List<Meal> userRatedMeals = new ArrayList<>();
        if (cafeteriaUser == null) return userRatedMeals;
        Iterable <Rating> listaRatings = PersistenceContext.repositories().ratings().findByCafeteriaUser(cafeteriaUser);
        for(Rating r : listaRatings){
            userRatedMeals.add(r.meal());
        }
        return userRatedMeals;
    }
    
    
}
