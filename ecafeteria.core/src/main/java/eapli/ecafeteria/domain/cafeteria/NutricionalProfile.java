package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.domain.meals.NutricionalInfo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
@Entity
public class NutricionalProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    private CafeteriaUser cafeteriaUser;
    @Version
    private Long version;

    @ManyToOne
    private NutricionalInfo weeklyNutritionalInfo;
    @ManyToOne
    private NutricionalInfo dailyNutritionalInfo;

    public NutricionalProfile() {
        //for ORM
    }

    public NutricionalProfile(CafeteriaUser user, Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt) {
        if (user == null) {
            throw new IllegalStateException();
        }
        this.cafeteriaUser = user;
        this.dailyNutritionalInfo = new NutricionalInfo(dailyCalories, dailySalt);
        this.weeklyNutritionalInfo = new NutricionalInfo(weeklyCalories, weeklySalt);
    }

    public CafeteriaUser cafeteriaUser() {
        return cafeteriaUser;
    }

    public NutricionalInfo dailyNutricionalinfo() {
        return dailyNutritionalInfo;
    }

    public NutricionalInfo weeklyNutricionalInfo() {
        return weeklyNutritionalInfo;
    }

    @Override
    public int hashCode() {
        return this.cafeteriaUser.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NutricionalProfile)) {
            return false;
        }
        NutricionalProfile other = (NutricionalProfile) object;
        if ((this.cafeteriaUser == null && other.cafeteriaUser != null) || (this.cafeteriaUser != null && !this.cafeteriaUser.equals(other.cafeteriaUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.NutritionalProfile[ id=" + cafeteriaUser + " ]";
    }

}
