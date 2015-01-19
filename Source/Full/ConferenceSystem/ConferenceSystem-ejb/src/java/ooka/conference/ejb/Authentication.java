/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import javax.ejb.Stateful;
import ooka.conference.dto.UserData;

/**
 *
 * @author bastian
 */
@Stateful
public class Authentication implements AuthenticationLocal {

    @Override
    public boolean registerUser(UserData data) {
        return true;
    }
}
