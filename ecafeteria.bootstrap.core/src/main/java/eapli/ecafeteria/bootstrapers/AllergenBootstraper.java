/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterAllergenController;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author JoãoPedro
 */
public class AllergenBootstraper implements Action {

    @Override
    public boolean execute() {
        register("glúten", "Cereais que contêm glúten (trigo, centeio, cevada, aveia, espelta, gamut ou outras estirpes hibridizadas) e produtos à base destes cereais");
        register("marisco", "Crustáceos e produtos à base de crustáceos");
        register("lactose", "Leite e produtos à base de leite (incluindo lactose)");
        register("sésamo", "Sementes de sésamo e produtos à base de sementes de sésamo");
        
        final AllergenRepository repo = PersistenceContext.repositories().allergens();
        Iterable<Allergen> it = repo.findAll();
        int i = 0;
        Iterator iter = it.iterator();
        
        while(iter.hasNext()){
            iter.next();
            i++;            
        }
        System.out.println("Numero de allergenios " + i);
        
        return false;
    }

    /**
     *
     */
    private void register(String name, String description) {
        final RegisterAllergenController controller = new RegisterAllergenController();
        try {
            controller.registerAllergen(name, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
