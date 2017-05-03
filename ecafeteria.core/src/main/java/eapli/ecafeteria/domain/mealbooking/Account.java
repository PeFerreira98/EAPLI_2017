/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import static eapli.ecafeteria.domain.mealbooking.TransactionType.DEBT;
import static eapli.ecafeteria.domain.mealbooking.TransactionType.LOAD;
import eapli.framework.domain.Money;
import eapli.util.Strings;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import static org.eclipse.persistence.sessions.SessionProfiler.Transaction;

/**
 *
 * This class represents the user account that stores his balance and
 * transactions
 *
 * @author Alexandra Ferreira 1140388
 */
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Money balance;

    /**
     * List of Transactions.
     */
    private List<Transaction> transactionList;

    protected Account() {
        // for ORM
        this.balance = new Money(0, Currency.getInstance(Locale.getDefault()));
        transactionList = new ArrayList<>();
    }

    public Account(String currency) {
        if (Strings.isNullOrEmpty(currency)) {
            throw new IllegalStateException("Currency should neither be null nor empty");
        }
        this.balance = new Money(0, Currency.getInstance(currency));
        transactionList = new ArrayList<>();
    }

    /**
     * Verify if the account has enough money.
     *
     * @param expenseValue
     * @return
     */
    public boolean hasSufficientBalance(Money expenseValue) {
        return (this.balance.greaterThanOrEqual(expenseValue));
    }

    /**
     * Updates the current balance of the account
     *
     * @param ammount to be added to the balance
     * @return operation result
     */
    private boolean updateBalance(Transaction transaction) {
        if (transaction.getType() == LOAD) {
            balance = balance.add(transaction.getAmount());
            return true;
        }
        
        if (transaction.getType() == DEBT) {
            balance = balance.subtract(transaction.getAmount());
            return true;
        }
        
        return false;
    }

    /**
     * Remove amount from the account.
     *
     * @param amount
     * @return
     */
    public boolean addAccountDebt(BigDecimal amount) {
        Money money = new Money(amount.doubleValue(), balance.currency());

        if (this.balance.lessThan(money)) {
            return false;
        }

        Transaction debtTransaction = new Transaction(money, DEBT);

        if (!transactionList.add(debtTransaction)) {
            return false;
        }

        updateBalance(debtTransaction);
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.mealbooking.Account[ id=" + id + " ]";
    }

    public Money getBalance() {
        return balance;
    }

}
