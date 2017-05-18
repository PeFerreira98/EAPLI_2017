/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterDishAllergenController;
import eapli.framework.actions.Action;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.logging.Logger;
/**
 *
 * @author JoãoPedro
 */
public class DishAllergenBootstraper implements Action {

    @Override
    public boolean execute() {
        final AllergenRepository allergenRepo = PersistenceContext.repositories().allergens();
        //Iterable<Allergen> itAllergen = allergenRepo.findAll();
        Allergen allergen1 = allergenRepo.findByName("glúten");
        Allergen allergen2 = allergenRepo.findByName("marisco");
        Allergen allergen3 = allergenRepo.findByName("lactose");
        Allergen allergen4 = allergenRepo.findByName("sésamo");
       
        final DishRepository dishRepo = PersistenceContext.repositories().dishes();
        //Iterable<Dish> itDish = dishRepo.findAll();
        Dish dish1 = dishRepo.findByName(Designation.valueOf("tofu grelhado"));
        
        
        register(dish1,allergen1);
        register(dish1,allergen2);
        register(dish1,allergen3);
        register(dish1,allergen4);
        
        final DishAllergenRepository repo = PersistenceContext.repositories().dishAllergens();
        Iterable<DishAllergen> it = repo.findAll();
        int i = 0;
        Iterator iter = it.iterator();
        
        while(iter.hasNext()){
            iter.next();
            i++;            
        }
        System.out.println("Numero de allergenicos do dish " + i);
        int expected = 4;
          int found = 0;
         DishAllergenRepository dishAllergenRepo = PersistenceContext.repositories().dishAllergens();
          
          Iterable<DishAllergen> itDA = dishAllergenRepo.findAll();
          for(DishAllergen da : itDA){
              if(da != null){
                  found++;
              }
          }
          System.out.println("\nDishAllergens found in database: " + found + " / "+ expected);
          
          if(found > 0){
              DishAllergen da = itDA.iterator().next();
              boolean test = da.is(dishRepo.findByName(Designation.valueOf("tofu grelhado")), allergenRepo.findByName("glúten"));
              if(!test){
                 System.out.println("   DishAllergen not found");  
              } else {                
                System.out.println("   Dish Allergen found: Dish , " + da.idDish() + " | Allergen, " + da.idAllergen()
                );
              }
          }
        return false;
    }

    /**
     *
     */
    private void register(Dish dish, Allergen allergen) {
        final RegisterDishAllergenController controller = new RegisterDishAllergenController();
        try {
            controller.registerDishAllergen(dish, allergen);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
