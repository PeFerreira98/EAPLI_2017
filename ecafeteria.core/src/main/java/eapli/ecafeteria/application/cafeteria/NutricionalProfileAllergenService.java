package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class NutricionalProfileAllergenService {

    private final NutricionalProfileAllergenRepository repo = PersistenceContext.repositories().nutricionalProfileAllergens();

    public Iterable<Allergen> findAllergensByMecNumber(String mecNumber) {

        return findAllergensByNutricionalProfile(
                PersistenceContext.repositories().nutricionalProfiles().findByCafeteriaUser(
                        PersistenceContext.repositories().cafeteriaUsers(
                                PersistenceContext.repositories().buildTransactionalContext()
                        ).findByMecanographicNumber(new MecanographicNumber(mecNumber))));
    }

    public Iterable<Allergen> findAllergensByUsername(String username) {

        return findAllergensByNutricionalProfile(
                PersistenceContext.repositories().nutricionalProfiles().findByCafeteriaUser(
                        PersistenceContext.repositories().cafeteriaUsers(
                                PersistenceContext.repositories().buildTransactionalContext()
                        ).findByUsername(new Username(username))));
    }

    public Iterable<Allergen> findAllergensByNutricionalProfile(NutricionalProfile user) {
        Iterable<NutricionalProfileAllergen> list = repo.findNutricionalProfileAllergenByNutricionalProfile(user);
        List<Allergen> allergens = new ArrayList<>();
        for (NutricionalProfileAllergen npa : list) {
            allergens.add(npa.allergen());
        }

        return allergens;
    }

    public NutricionalProfileAllergen add(NutricionalProfile profile, Allergen allergen) throws DataConcurrencyException, DataIntegrityViolationException {
        return repo.save(new NutricionalProfileAllergen(profile, allergen));
    }

    public void remove(NutricionalProfile profile, Allergen allergen) {
        for (NutricionalProfileAllergen npa : repo.findNutricionalProfileAllergenByNutricionalProfile(profile)) {
            if (allergen.equals(npa.allergen())) {
                try {
                    repo.delete(npa);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(NutricionalProfileAllergenService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
