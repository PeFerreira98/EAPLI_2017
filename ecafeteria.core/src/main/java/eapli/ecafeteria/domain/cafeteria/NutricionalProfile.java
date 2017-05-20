package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.domain.meals.NutricionalInfo;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
@Entity
public class NutricionalProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

//    @Id
//    @GeneratedValue
//    private Long id;
    @EmbeddedId
    private MecanographicNumber id;

//    @OneToOne
//    private CafeteriaUser cafeteriaUser;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "calories", column = @Column(name = "weeklycalories")),
        @AttributeOverride(name = "salt", column = @Column(name = "weeklysalt"))
    })
    private NutricionalInfo weeklyNutritionalInfo;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "calories", column = @Column(name = "dailycalories")),
        @AttributeOverride(name = "salt", column = @Column(name = "dailysalt"))
    })
    private NutricionalInfo dailyNutritionalInfo;

    public NutricionalProfile() {
        //for ORM
    }

    public NutricionalProfile(CafeteriaUser user, Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt) {
        if (user == null) {
            throw new IllegalStateException();
        }
//        this.cafeteriaUser = user;
        this.id = user.id();
        this.dailyNutritionalInfo = new NutricionalInfo(dailyCalories, dailySalt);
        this.weeklyNutritionalInfo = new NutricionalInfo(weeklyCalories, weeklySalt);
    }

    public MecanographicNumber id() {
        return id;
    }

//    public CafeteriaUser cafeteriaUser() {
//        return cafeteriaUser;
//    }
    public NutricionalInfo dailyNutricionalinfo() {
        return dailyNutritionalInfo;
    }

    public NutricionalInfo weeklyNutricionalInfo() {
        return weeklyNutritionalInfo;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NutricionalProfile)) {
            return false;
        }
        NutricionalProfile other = (NutricionalProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.NutritionalProfile[ id=" + id + " ]";
    }

}
