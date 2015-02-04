/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import ooka.conference.viewControllers.LoginRegController;

/**
 *
 * @author Bastian
 */
public class Redirector {

    private Redirector() {

    }

    public static void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./" + page + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
