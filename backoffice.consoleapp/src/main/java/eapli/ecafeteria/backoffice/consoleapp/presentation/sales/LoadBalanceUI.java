/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.sales;

import eapli.ecafeteria.application.sales.LoadBalanceController;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class LoadBalanceUI extends AbstractUI {

    private final LoadBalanceController controller = new LoadBalanceController();

    @Override
    protected boolean doShow() {

        CafeteriaUser user = null;
        while (user == null) {
            final String username = Console.readLine("Username of user: ");

            try {
                user = this.controller.getCafeteriaUser(username);
            } catch (NoSuchElementException e) {
                System.out.println("No account was found with this number.\n");
            }
        }
        System.out.println("Cafeteria User accepted.");
        
        String amount = "0";

        while (Double.parseDouble(amount) < 1) {
            amount = Console.readLine("\nAmount: ");

            if (Double.parseDouble(amount) < 1) {
                System.out.println("Invalid amount of money.");
            }
        }
        
        try {
            if(this.controller.loadAccount(user, BigDecimal.valueOf(Double.parseDouble(amount)))){
                System.out.println("Load Balance successful.");
            }
        } catch (Exception e) {
            System.out.println("There was a problem loading the account.\n" + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Load Balance";
    }
}
