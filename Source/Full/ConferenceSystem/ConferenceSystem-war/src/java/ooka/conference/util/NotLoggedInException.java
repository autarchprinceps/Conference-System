/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.util;

/**
 *
 * @author Bastian
 */
public class NotLoggedInException extends Exception {

    public NotLoggedInException() {
        super("User is not logged in!");
    }

}
