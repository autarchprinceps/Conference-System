package ooka.conference.appControllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.conference.dto.UserData;
import ooka.conference.ejb.UserAdministrationLocal;
import ooka.conference.entity.User;
import ooka.conference.util.Redirector;
import ooka.conference.util.WrongLoginCredentialsException;

@ManagedBean
@SessionScoped
public class AuthenticationController {

    @EJB
    private UserAdministrationLocal userEJB;

    private User currentUser;

    public void loginUser(UserData data) throws WrongLoginCredentialsException {
        User user = userEJB.validateUser(data);
        if (user == null) {
            throw new WrongLoginCredentialsException();
        }

        currentUser = user;
    }

    public boolean logoutUser() {
        currentUser = null;
        return true;
    }

    public boolean isLoggedIn() {
        return !(currentUser == null);
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

    public boolean registerUser(UserData data) {
        userEJB.registerUser(data);
        return true;
    }

}
