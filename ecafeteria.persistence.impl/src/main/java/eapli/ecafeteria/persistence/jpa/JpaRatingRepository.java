/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.ecafeteria.persistence.RatingRepository;

/**
 *
 * @author Pedro Pereira
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements RatingRepository {

    @Override
    public Iterable<Rating> findByMeal(Meal meal) {
        return match("e.meal='"+meal+"'");
    }

    @Override
    public Iterable<Rating> findByCafeteriaUser(CafeteriaUser cafeteriaUser) {
        return match("e.cafeteriaUser='"+cafeteriaUser+"'");
    }

    @Override
    public Iterable<Rating> findByMealAndCafeteriaUser(Meal meal, CafeteriaUser cafeteriaUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
