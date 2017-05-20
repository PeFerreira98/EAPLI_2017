package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
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
import java.util.logging.Logger;

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

        final NutricionalProfile newNutricionalProfile = new NutricionalProfile(user, dailyCalories, dailySalt, weeklyCalories, weeklySalt);
        return this.repository.save(newNutricionalProfile);
    }
    
    public NutricionalProfileAllergen registerNutricionalProfileAllergen(NutricionalProfile profile, Allergen allergen)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        final NutricionalProfileAllergen newNutricionalProfileAllergen = new NutricionalProfileAllergen(profile, allergen);
        return this.repositoryAllergen.save(newNutricionalProfileAllergen);
    }

    /**
     * Obtem o cafeteriauser que esta actualmente logado. Se nao existir,
     * devolve null.
     *
     * @return o cafeteria user que esta actualmente logado. Se nao existir,
     * devolve null.
     */
    public CafeteriaUser obtainCurrentCafeteriaUser() {
        CafeteriaUser activeCafeteriaUser = null;
        try {
            Username username = Application.session().session().authenticatedUser().username();
            activeCafeteriaUser = new CafeteriaUserService().findCafeteriaUserByUsername(username);
        } catch (javax.persistence.PersistenceException ex) {
            String error = "Error getting the CafeteriaUser of logged SystemUser.   " + ex;
            Logger.getGlobal().severe(error);
        }

        return activeCafeteriaUser;
    }

}
