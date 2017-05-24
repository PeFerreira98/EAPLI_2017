package eapli.ecafeteria.domain.alert;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    private Integer redAlert;
    private Integer yellowAlert;

    public BookingAlert() {
        // for ORM
    }

    public BookingAlert(Integer redAlert, Integer yellowAlert) {
        this.redAlert = redAlert;
        this.yellowAlert = yellowAlert;
    }

    public Integer redAlert() {
        return redAlert;
    }

    public Integer yellowAlert() {
        return yellowAlert;
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
            //TODO
        }
    }

}
