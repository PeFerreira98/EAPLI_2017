/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Servico cuja responsabilidade e detectar allergenicos e alergias de users e Meals. OBSERVABLE
 * @author Pedro Pereira
 */
public class AllergyDetectionService extends Observable{
    
    /**
     * Determina se um determinado user é alergico a uma meal. OBSERVABLE
     * @param cafeteriaUser cafeteria user a vereficar.
     * @param meal meal a vereficar
     * @return true de alergico, false se não ou impossivel comparar.
     */
    public boolean isCafeteriaUserAllergicToMeal(CafeteriaUser cafeteriaUser, Meal meal){
        if(cafeteriaUser == null || meal == null) return false;
        boolean wasAllergyDetected = false;

        
        List <Allergen> listaDeAllegenicosComuns = this.listCommonAllergen(cafeteriaUser, meal);
        
        if (! listaDeAllegenicosComuns.isEmpty()) wasAllergyDetected = true;    //Se a lista nao esta vazia entao vai existir alergia.
        
        if(wasAllergyDetected){             //Se detectamos uma alergia,
            this.setChanged();              // Vamos informar que estamos alterados
            this.notifyObservers();         // Se estamos alterados, vai informar todos os observadores.
        }
        
        return wasAllergyDetected;
    }
    
    /**
     * Metodo que devolve todos os alergenicos existentes numa Meal.
     * @param meal meal a obter os alergenicos
     * @return List de Allergen da meal
     */
    public List<Allergen> listAllergen(Meal meal){
        List<Allergen> listaAllergenicos = new ArrayList();
        if(meal == null) return listaAllergenicos;
        
        Dish dish = meal.dish();
        
        
        // Obter uma lista com todos os DishAllergen do Dish.
        // Ir a cada DishAllerger, obter o Allergen e addicionar a lista.
        
        // FIXME FIX ME Corrigir os erros nos repositorios para nao termos de usar getALL nos repositorios.
        try {

            DishAllergenRepository dishAllergenRepository = PersistenceContext.repositories().dishAllergens();
            
            // O repo find by dish está quebrado, portanto vamos primeiro obter os DishAlergen do nosso dish.
            //Iterable<DishAllergen> iterableDishAllergen = dishAllergenRepository.findByDish(dish);
            
            Iterable<DishAllergen> iterableDishAllergen = dishAllergenRepository.findAll();

            AllergenRepository allergenRepository = PersistenceContext.repositories().allergens();
            Iterable<Allergen> iterableAllergen = allergenRepository.findAll();

            for(DishAllergen dishAllergen : iterableDishAllergen){
                for(Allergen allergen : iterableAllergen){
                    if(dishAllergen.is(dish, allergen)){
                        if(allergen.isActive() && dishAllergen.isActive()){
                            listaAllergenicos.add(allergen);
                        }
                    }
                }
            }
        } catch (javax.persistence.PersistenceException ex){
            String error = "Error interacting with dishAllergenRepository.   " + ex;
            Logger.getGlobal().severe(error);
        }    
        
        /*  DEBUG!!!!
        for(Allergen a : listaAllergenicos){
            System.out.println("Debug:" + a.id());
        }
       */
        
        return listaAllergenicos;
    }
    
    
    /**
     * Metodo que devolve todos os alergenicos associados a um CafeteriaUser
     * @param cafeteriaUser cafeteriaUser do qual obter os alergenicos
     * @return List de Allergen associados ao cafeteriaUser
     */
    public List<Allergen> listAllergen(CafeteriaUser cafeteriaUser){
        List<Allergen> listaAllergenicos = new ArrayList();
        if (cafeteriaUser == null)  return listaAllergenicos;
        
        //TODO
        // Obter uma lista com todos os AllergenProfile do CafeteriaUser
        // Obter os Allergen do AllergenProfiles e adicionar a lista.         
        System.out.println("FALTA IMPLEMENTAR listAllergen(CafeteriaUser cafeteriaUser) Hugo & Pedro");
        
        return listaAllergenicos;
    }
    
    /**
     * Devolve uma lista com os allergenicos em commum entre um user e uma meal.
     * @param cafeteriaUser o cafeteria User
     * @param meal a meal em questao
     * @return a lista de allergenicos presentes na meal e no perfil do cafeteriaUser
     */
    public List<Allergen> listCommonAllergen(CafeteriaUser cafeteriaUser, Meal meal){
        
        List <Allergen> listaUser = this.listAllergen(cafeteriaUser);
        List <Allergen> listaMeal = this.listAllergen(meal);
        List <Allergen> listaCommon = new ArrayList();
        
        //Comparamos todos os alergenicos de uma lista com o de outra 
        // e se o allergenico for o mesmo e nao estiver na lista, adicionamos.
        for(Allergen allergenUser : listaUser){
            for(Allergen allergenMeal : listaMeal){
                if(allergenUser.equals(allergenMeal) && !listaCommon.contains(allergenUser)) listaCommon.add(allergenUser);
            }
        }
        
        return listaCommon;
    }
    
    
    
    
}
