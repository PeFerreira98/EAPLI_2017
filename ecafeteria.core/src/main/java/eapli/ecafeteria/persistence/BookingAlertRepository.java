package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.alert.Alert;
import eapli.ecafeteria.domain.alert.BookingAlert;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public interface BookingAlertRepository extends DataRepository<BookingAlert, Alert> {

    BookingAlert findBookingAlertByAlert(Alert alert);
}
