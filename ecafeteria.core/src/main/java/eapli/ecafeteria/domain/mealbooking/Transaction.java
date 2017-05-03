/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import eapli.framework.domain.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
@Embeddable
public class Transaction implements ValueObject{
    
    private Money money;
    
    private TransactionType type;
    
    protected Transaction() {
        // for ORM
    }
    
    public Transaction(Money money, TransactionType type) {
        this.type = type;

        if (money.amount() < 0) {
            throw new IllegalArgumentException();
        }
        
        this.money = money;
    }

    public TransactionType getType() {
        return this.type;
    }

    public Money getAmount() {
        return this.money;
    }
    
    
    
}
