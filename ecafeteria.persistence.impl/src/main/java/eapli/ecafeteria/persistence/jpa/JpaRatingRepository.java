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
import javax.persistence.Query;

/**
 *
 * @author Pedro Pereira
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements RatingRepository {

    @Override
    public Iterable<Rating> findByMeal(Meal meal) {
        //return match("e.meal='" + meal + "'");
        
        Query createQuery = entityManager().createQuery("SELECT r FROM Rating r WHERE r.meal=:meal");
        createQuery.setParameter("meal", meal);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Rating> findByCafeteriaUser(CafeteriaUser cafeteriaUser) {
        //return match("e.cafeteriaUser='" + cafeteriaUser + "'");
        
        Query createQuery = entityManager().createQuery("SELECT r FROM Rating r WHERE r.cafeteriaUser=:user");
        createQuery.setParameter("user", cafeteriaUser);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Rating> findByMealAndCafeteriaUser(Meal meal, CafeteriaUser cafeteriaUser) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Rating r WHERE r.cafeteriaUser=:user AND r.meal=:meal");
        createQuery.setParameter("user", cafeteriaUser);
        createQuery.setParameter("meal", meal);
        return createQuery.getResultList();
    }
    
}
