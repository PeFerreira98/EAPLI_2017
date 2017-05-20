package eapli.ecafeteria.domain.cafeteria;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
@Embeddable
public class NutricionalProfileAllergenId implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    public NutricionalProfileAllergenId() {
        // for ORM
    }

    public NutricionalProfileAllergenId(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new IllegalArgumentException("cafetria name cannot be empty neither null");
        }
        setId(id);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
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
        if (!(object instanceof NutricionalProfileAllergenId)) {
            return false;
        }
        NutricionalProfileAllergenId other = (NutricionalProfileAllergenId) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergenId[ id=" + id + " ]";
    }

}
