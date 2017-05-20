package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.domain.meals.Allergen;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
@Entity
public class NutricionalProfileAllergen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String id;

    @ManyToOne
    private NutricionalProfile nutricionalProfile;

    @ManyToOne
    private Allergen allergen;

    public NutricionalProfileAllergen() {
        //for ORM
    }

    public String id() {
        return id;
    }

    public NutricionalProfile nutricionalProfile() {
        return nutricionalProfile;
    }

    public Allergen allergen() {
        return allergen;
    }

    public NutricionalProfileAllergen(NutricionalProfile nutricionalProfile, Allergen allergen) {
        if (nutricionalProfile == null || allergen == null) {
            throw new IllegalArgumentException("Null parameter inserted");
        }
        this.nutricionalProfile = nutricionalProfile;
        this.allergen = allergen;
        this.id = nutricionalProfile.id() + allergen.id();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nutricionalProfile);
        hash = 79 * hash + Objects.hashCode(this.allergen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NutricionalProfileAllergen other = (NutricionalProfileAllergen) obj;
        if (!Objects.equals(this.nutricionalProfile, other.nutricionalProfile)) {
            return false;
        }
        if (!Objects.equals(this.allergen, other.allergen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen[ id=" + nutricionalProfile + " ]";
    }

}
