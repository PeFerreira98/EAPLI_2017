/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.UserRegisterController;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.framework.actions.Action;

/**
 * @author pgsou_000
 */
public class UsersBootstrap implements Action {

    @Override
    public boolean execute() {
        registerAdmin();
        registerCashier();
        registerUser();
        registerKitchenManager();
        registerMenuManager();
        return false;
    }

    /**
     *
     */
    private void registerAdmin() {
        final String username = "admin";
        final String password = "admin";

        final String firstName = "Mary";
        final String lastName = "Admin";

        final String email = "mary.doe@emai.l.com";

        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.Admin);

        final UserRegisterController userController = new UserRegisterController();
        try {
            userController.registerUser(username, password, firstName, lastName, email, roles);
        } catch (final Exception e) {
            // ignoring exception. assuming it is justa primiray key violation
            // due to the tentative of inserting a duplicated user
        }
    }

    private void registerCashier() {
        final String username = "cashier";
        final String password = "cashier";

        final String firstName = "Johny";
        final String lastName = "Cash";

        final String email = "johny.doe@emai.l.com";

        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.Cashier);

        final UserRegisterController userController = new UserRegisterController();
        try {
            userController.registerUser(username, password, firstName, lastName, email, roles);
        } catch (final Exception e) {
            // ignoring exception. assuming it is justa primiray key violation
            // due to the tentative of inserting a duplicated user
        }
    }

    private void registerUser() {
        final String username = "user";
        final String password = "user";

        final String firstName = "The";
        final String lastName = "User";

        final String email = "the.user@emai.l.com";

        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.User);

        final UserRegisterController userController = new UserRegisterController();
        try {
            userController.registerUser(username, password, firstName, lastName, email, roles);
        } catch (final Exception e) {
            // ignoring exception. assuming it is justa primiray key violation
            // due to the tentative of inserting a duplicated user
        }
    }

    private void registerKitchenManager() {
        final String username = "kitchen";
        final String password = "kitchen";

        final String firstName = "Oven";
        final String lastName = "Stove";

        final String email = "Oven.and.stove@emai.l.com";

        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.KitchenManager);

        final UserRegisterController userController = new UserRegisterController();
        try {
            userController.registerUser(username, password, firstName, lastName, email, roles);
        } catch (final Exception e) {
            // ignoring exception. assuming it is justa primiray key violation
            // due to the tentative of inserting a duplicated user
        }
    }

    private void registerMenuManager() {
        final String username = "chef";
        final String password = "chef";

        final String firstName = "Master";
        final String lastName = "Chef";

        final String email = "master.chef@emai.l.com";

        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.MenuManager);

        final UserRegisterController userController = new UserRegisterController();
        try {
            userController.registerUser(username, password, firstName, lastName, email, roles);
        } catch (final Exception e) {
            // ignoring exception. assuming it is justa primiray key violation
            // due to the tentative of inserting a duplicated user
        }
    }
}
