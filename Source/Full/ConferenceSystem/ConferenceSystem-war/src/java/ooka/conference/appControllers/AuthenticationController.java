package ooka.conference.appControllers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.conference.dto.UserData;
import ooka.conference.ejb.UserAdministrationLocal;
import ooka.conference.entity.User;
import ooka.conference.util.WrongLoginCredentialsException;

@ManagedBean
@SessionScoped
public class AuthenticationController implements Serializable {

    @EJB
    private UserAdministrationLocal userEJB;

    private User currentUser;

    public void loginUser(UserData data) throws Exception {

        if (isLoggedIn()) {
            throw new Exception("Already logged in!");
        }

        try {
            currentUser = userEJB.validateUser(data);
        } catch (Exception e) {
            currentUser = null;
            throw new WrongLoginCredentialsException();
        }

        if (currentUser == null) {
            throw new WrongLoginCredentialsException();
        }

    }

    public void logoutUser() {
        currentUser = null;
        PageController.redirectTo(PageController.userLoginPage);
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        if (!isLoggedIn()) {
            // force to login
            PageController.redirectTo(PageController.userLoginPage);
            return null;
        } else {
            return currentUser;
        }
    }

    public void registerUser(UserData data) throws Exception {
        userEJB.registerUser(data);
    }

}
