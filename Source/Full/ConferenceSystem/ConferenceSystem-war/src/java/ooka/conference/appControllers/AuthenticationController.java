package ooka.conference.appControllers;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import ooka.conference.dto.UserData;
import ooka.conference.ejb.UserAdministrationLocal;

@SessionScoped
public class AuthenticationController {

    @EJB
    private UserAdministrationLocal userEJB;
    
    private boolean loggedIn;
    private int userId;
    
    public boolean loginUser(UserData data) {

        return true;
    }

    public boolean logoutUser() {

        return true;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getUserId() {
        return userId;
    }

}
