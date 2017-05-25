package eapli.ecafeteria.domain.alert;

import eapli.ecafeteria.domain.mealbooking.Booking;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
@Entity
public class BookingAlert implements Observer, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Enumerated(EnumType.STRING)
    private Alert id;
    @Version
    private Long version;

    private Integer alertLevel;

    public BookingAlert() {
        // for ORM
    }

    public BookingAlert(Alert alert, Integer alertLevel) {
        this.id = alert;
        this.alertLevel = alertLevel;
    }

    public Alert id() {
        return id;
    }

    public Integer alertLevel() {
        return alertLevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingAlert)) {
            return false;
        }
        BookingAlert other = (BookingAlert) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.domain.alert.BookingAlert[ id=" + id + " ]";
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o != null && o1 != null) {
            if (o1 instanceof Booking) {
                //FIXME not possible to get the reserved quantity
                // MealPlan and MealPlanItem not found in database
                Integer reservedQty = 4; // values just to test alerts
                Integer maxQty = 4;

                if ((1.0 * reservedQty / maxQty) * 100 >= alertLevel) {
                    System.out.println(id.toString() + " alert! " + alertLevel + "% of reservations reached");
                }
            }
        }
    }

}
