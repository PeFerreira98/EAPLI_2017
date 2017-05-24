package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class RegisterNutricionalProfileController implements Controller {

    private final NutricionalProfileRepository repository = PersistenceContext.repositories().nutricionalProfiles();
    private final NutricionalProfileAllergenRepository repositoryAllergen = PersistenceContext.repositories().nutricionalProfileAllergens();

    public NutricionalProfile registerNutricionalProfile(CafeteriaUser user, Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        if (new NutricionalProfileService().findCurrentUserNutricionalProfile() != null) {
            throw new IllegalStateException("User already has a profile");
        }

        final NutricionalProfile newNutricionalProfile = new NutricionalProfile(user, dailyCalories, dailySalt, weeklyCalories, weeklySalt);
        return this.repository.save(newNutricionalProfile);
    }

    public NutricionalProfile registerNutricionalProfile(Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        if (new NutricionalProfileService().findCurrentUserNutricionalProfile() != null) {
            throw new IllegalStateException("User already has a profile");
        }

        return this.registerNutricionalProfile(new CafeteriaUserService().obtainCurrentCafeteriaUser(), dailyCalories, dailySalt, weeklyCalories, weeklySalt);
    }

    public NutricionalProfileAllergen registerNutricionalProfileAllergen(NutricionalProfile profile, Allergen allergen)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        if (new NutricionalProfileService().findCurrentUserNutricionalProfile() != null) {
            throw new IllegalStateException("User already has a profile");
        }

        final NutricionalProfileAllergen newNutricionalProfileAllergen = new NutricionalProfileAllergen(profile, allergen);
        return this.repositoryAllergen.save(newNutricionalProfileAllergen);
    }

    public void registerNutricionalProfileAllergens(NutricionalProfile profile, Iterable<Allergen> allergens)
            throws DataIntegrityViolationException, DataConcurrencyException {
        for (Allergen a : allergens) {
            registerNutricionalProfileAllergen(profile, a);
        }
    }

}
