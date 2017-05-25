package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.Account;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * A Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. A Cafeteria User references a System User
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    private SystemUser systemUser;

    @ManyToOne(cascade = CascadeType.MERGE)
    private OrganicUnit organicUnit;

    @OneToOne(cascade = CascadeType.MERGE)
    private Account account;

    public CafeteriaUser(SystemUser user, OrganicUnit organicUnit, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null || organicUnit == null) {
            throw new IllegalStateException();
        }
        this.systemUser = user;
        this.organicUnit = organicUnit;
        this.mecanographicNumber = mecanographicNumber;
        this.account = new Account("EUR");
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber) && this.systemUser.sameAs(that.systemUser)
                && this.organicUnit.sameAs(that.organicUnit));
    }

    @Override
    public boolean is(MecanographicNumber id) {
        return id().equals(id);
    }

    public MecanographicNumber mecanographicNumber() {
        return id();
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    public OrganicUnit organicUnit() {
        return this.organicUnit;
    }

    public Account account() {
        return this.account;
    }

    public Money accountBalance() {
        return this.account.getBalance();
    }

    public boolean hasSufficientBalance(Money expenseValue) {
        return this.account.hasSufficientBalance(expenseValue);
    }

    public boolean registerExpense(BigDecimal value) {
        return this.account.addAccountDebt(value);
    }

    public boolean registerLoad(BigDecimal value) {
        return this.account.addAccountLoad(value);
    }
}
