package eapli.ecafeteria.domain.alert;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.Entity;
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
            //TODO get the reserved and planned quantity
            Integer reservedQty = 3;
            Integer maxQty = 4;

            if ((reservedQty / maxQty) * 100 > alertLevel) {
                System.out.println(id.toString() + " alert! " + alertLevel + "% of reservations reached");
            }
        }
    }

}
