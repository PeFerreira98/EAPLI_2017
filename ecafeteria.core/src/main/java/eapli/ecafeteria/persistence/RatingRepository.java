/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Pedro Pereira
 */
public interface RatingRepository extends DataRepository<Rating, Long>{
    
    Iterable<Rating> findByMeal(Meal meal);
    
    Iterable<Rating> findByCafeteriaUser(CafeteriaUser cafeteriaUser);
    
    Iterable<Rating> findByMealAndCafeteriaUser(Meal meal , CafeteriaUser cafeteriaUser);
}
