/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
public class CafeteriaUserService {

    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers(null);

    public CafeteriaUser findCafeteriaUserByMecNumber(String mecNumber) {
        return this.repo.findByMecanographicNumber(new MecanographicNumber(mecNumber));
    }

    public CafeteriaUser findCafeteriaUserByUsername(Username user) {
        return this.repo.findByUsername(user);
    }

    /**
     * Obtem o cafeteriauser que esta actualmente logado. Se nao existir,
     * devolve null.
     *
     * @return o cafeteria user que esta actualmente logado. Se nao existir,
     * devolve null.
     */
    public CafeteriaUser obtainCurrentCafeteriaUser() {
        CafeteriaUser activeCafeteriaUser = null;
        try {
            Username username = Application.session().session().authenticatedUser().username();
            activeCafeteriaUser = findCafeteriaUserByUsername(username);
        } catch (javax.persistence.PersistenceException ex) {
            String error = "Error getting the CafeteriaUser of logged CafeteriaUser.   " + ex;
            Logger.getGlobal().severe(error);
        }

        return activeCafeteriaUser;
    }
}
