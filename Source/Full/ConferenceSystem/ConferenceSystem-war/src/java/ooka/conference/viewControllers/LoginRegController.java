package ooka.conference.viewControllers;

import javax.annotation.PostConstruct;
import ooka.conference.appControllers.PageController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.dto.UserData;

@ManagedBean
@ViewScoped
public class LoginRegController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    private String registrationPassword;
    private String registrationUsername;
    private String loginPassword;
    private String loginUserame;

    @PostConstruct
    public void init() {
        if (authEJB.isLoggedIn()) {
            loginUserame = authEJB.getCurrentUser().getName();
            loginPassword = authEJB.getCurrentUser().getPassword();
        }
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    public String getLoginUserame() {
        return loginUserame;
    }

    public void setLoginUserame(String loginUserame) {
        this.loginUserame = loginUserame;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String passwordLogin) {
        this.loginPassword = passwordLogin;
    }

    public String getRegistrationUsername() {
        return registrationUsername;
    }

    public void setRegistrationUsername(String registrationUsername) {
        this.registrationUsername = registrationUsername;
    }

    public String getRegistrationPassword() {
        return registrationPassword;
    }

    public void setRegistrationPassword(String registrationPassword) {
        this.registrationPassword = registrationPassword;
    }

    public void doLogin() {

        UserData data = new UserData(loginUserame, loginPassword);
        try {
            authEJB.loginUser(data);
        } catch (Exception ex) {
            return;
        }

        PageController.redirectTo(PageController.userViewPage);
    }

    public void doLogout() {
        authEJB.logoutUser();
        PageController.redirectTo(PageController.userLoginPage);
    }

    public void doRegister() {
        UserData data = new UserData(registrationUsername, registrationPassword);

        try {
            authEJB.registerUser(data);
        } catch (Exception ex) {
            return;
        }

        PageController.redirectTo(PageController.userLoginPage);
    }
}
