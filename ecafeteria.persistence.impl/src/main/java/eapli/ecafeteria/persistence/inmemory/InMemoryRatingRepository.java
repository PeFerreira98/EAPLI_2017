/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.rating.Rating;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro Pereira
 */
public class InMemoryRatingRepository extends InMemoryRepositoryWithLongPK<Rating> implements RatingRepository {

    @Override
    public Iterable<Rating> findByMeal(Meal meal) {
        return match(e -> e.meal().equals(meal));
    }

    @Override
    public Iterable<Rating> findByCafeteriaUser(CafeteriaUser cafeteriaUser) {
        return match(e -> e.cafeteriaUser().equals(cafeteriaUser));
    }

    @Override
    public Iterable<Rating> findByMealAndCafeteriaUser(Meal meal, CafeteriaUser cafeteriaUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
