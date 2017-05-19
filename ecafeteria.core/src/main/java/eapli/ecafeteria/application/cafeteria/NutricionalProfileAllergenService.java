package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class NutricionalProfileAllergenService {

    private final NutricionalProfileAllergenRepository repo = PersistenceContext.repositories().nutricionalProfileAllergens();

    public Iterable<Allergen> findAllergenByMecNumber(String mecNumber) {

        return findAllergenByNutricionalProfile(
                PersistenceContext.repositories().nutricionalProfiles().findByCafeteriaUser(
                        PersistenceContext.repositories().cafeteriaUsers(
                                PersistenceContext.repositories().buildTransactionalContext()
                        ).findByMecanographicNumber(new MecanographicNumber(mecNumber))));
    }

    public Iterable<Allergen> findAllergenByUsername(String username) {

        return findAllergenByNutricionalProfile(
                PersistenceContext.repositories().nutricionalProfiles().findByCafeteriaUser(
                        PersistenceContext.repositories().cafeteriaUsers(
                                PersistenceContext.repositories().buildTransactionalContext()
                        ).findByUsername(new Username(username))));
    }

    public Iterable<Allergen> findAllergenByNutricionalProfile(NutricionalProfile user) {
        Iterable<NutricionalProfileAllergen> list = repo.findNutricionalProfileAllergenByNutricionalProfile(user);
        List<Allergen> allergens = new ArrayList<>();
        for (NutricionalProfileAllergen npa : list) {
            allergens.add(npa.allergen());
        }

        return allergens;
    }
}
