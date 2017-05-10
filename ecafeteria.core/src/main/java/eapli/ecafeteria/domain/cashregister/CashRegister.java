/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cashregister;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
@Entity
public class CashRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private CashRegisterState state;

    protected CashRegister() {

    }

    /**
     * Constructor with the cash register number
     *
     * @param number Cash Register number
     */
    public CashRegister(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("New Cash Register missing arguments.");
        }
        this.number = number;
        this.state = CashRegisterState.CLOSED;
    }

    /**
     * Get cash register state
     *
     * @return cash register state
     */
    public CashRegisterState state() {
        return this.state;
    }

    /**
     * Get cash register number
     *
     * @return cash register number
     */
    public String number() {
        return number;
    }

    /**
     * Method that opens the register. return true if it does, false if it's
     * already opened
     *
     * @return true if cash register can be opened,otherwise return false
     */
    public boolean open() {
        if (this.state.equals(CashRegisterState.CLOSED)) {
            this.state = CashRegisterState.OPENED;
            return true;
        }
        return false;
    }

    /**
     * Method that closes the register. return true if it does, false if it's
     * already closed
     *
     * @return true if cash register can be closed,otherwise return false
     */
    public boolean close() {
        if (this.state.equals(CashRegisterState.OPENED)) {
            this.state = CashRegisterState.CLOSED;
            return true;
        }

        return false;
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
        if (!(object instanceof CashRegister)) {
            return false;
        }
        CashRegister other = (CashRegister) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.CashRegister[ id=" + id + " ]";
    }
}
