/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.UserData;

/**
 *
 * @author bastian
 */
@Local
public interface AuthenticationLocal {
    
    public boolean registerUser(UserData data);
    
}
