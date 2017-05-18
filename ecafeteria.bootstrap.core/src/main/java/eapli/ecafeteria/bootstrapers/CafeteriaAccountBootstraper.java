/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.framework.actions.Action;
import eapli.framework.domain.Money;
import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class CafeteriaAccountBootstraper implements Action{

    @Override
    public boolean execute() {
        CafeteriaUserService service = new CafeteriaUserService();
        CafeteriaUser user = service.findCafeteriaUserByUsername(new Username("900330"));
        user.account().addAccountLoad(new BigDecimal(9999999));
        
        return false;
    }
    
}
