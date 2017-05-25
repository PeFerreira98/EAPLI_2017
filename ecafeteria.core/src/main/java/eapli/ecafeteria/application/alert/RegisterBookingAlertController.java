package eapli.ecafeteria.application.alert;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.alert.Alert;
import eapli.ecafeteria.domain.alert.BookingAlert;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.BookingAlertRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class RegisterBookingAlertController implements Controller {

    private final BookingAlertRepository repo = PersistenceContext.repositories().bookingAlerts();

    public BookingAlert registerNutricionalProfile(Alert alert, Integer value)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        final BookingAlert newAlert = new BookingAlert(alert, value);
        return this.repo.save(newAlert);
    }

}
