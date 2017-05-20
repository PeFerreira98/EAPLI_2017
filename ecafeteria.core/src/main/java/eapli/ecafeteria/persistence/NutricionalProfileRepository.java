/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author zero_
 */
public interface NutricionalProfileRepository extends DataRepository<NutricionalProfile, MecanographicNumber> {

    NutricionalProfile findByCafeteriaUser(CafeteriaUser user);

}
