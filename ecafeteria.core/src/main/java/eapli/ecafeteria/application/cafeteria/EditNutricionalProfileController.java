package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class EditNutricionalProfileController implements Controller {

    private final NutricionalProfileRepository repository = PersistenceContext.repositories().nutricionalProfiles();
    private final NutricionalProfileAllergenService allergenService = new NutricionalProfileAllergenService();
    private final NutricionalProfile profile = new NutricionalProfileService().findCurrentUserNutricionalProfile();

    public void editProfile(Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt)
            throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        if (profile == null) {
            throw new IllegalStateException("User does not have a profile to edit");
        }
        profile.edit(dailyCalories, dailySalt, weeklyCalories, weeklySalt);
        repository.save(profile);
    }

    public void addAllergen(Allergen allergen) {
        try {
            allergenService.add(profile, allergen);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(EditNutricionalProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeAllergen(Allergen allergen) {
        allergenService.remove(profile, allergen);
    }

    public Iterable<Allergen> UserAllergens() {
        return allergenService.findAllergensByNutricionalProfile(profile);
    }
}
