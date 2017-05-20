package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.RegisterNutricionalProfileController;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class NutricionalProfileAllergenBootsraper implements Action {

    @Override
    public boolean execute() {

        final NutricionalProfileRepository repoNp = PersistenceContext.repositories().nutricionalProfiles();
        final NutricionalProfile profile1 = repoNp.findByMecNumber(new MecanographicNumber("900330"));
        final NutricionalProfile profile2 = repoNp.findByMecNumber(new MecanographicNumber("900331"));

        final AllergenRepository repoAl = PersistenceContext.repositories().allergens();
        final Allergen a1 = repoAl.findByName("marisco");
        final Allergen a2 = repoAl.findByName("lactose");
        final Allergen a3 = repoAl.findByName("marisco");

        register(profile1, a1);
        register(profile1, a2);
        register(profile2, a3);
        register(profile2, a1);

        return false;
    }

    private void register(final NutricionalProfile profile, final Allergen allergen) {
        final RegisterNutricionalProfileController controller = new RegisterNutricionalProfileController();
        try {
            controller.registerNutricionalProfileAllergen(profile, allergen);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
