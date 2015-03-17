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
public class UserLoginRegController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authController;

    private String registrationPassword;
    private String registrationUsername;
    private String loginPassword;
    private String loginUserame;

    @PostConstruct
    public void init() {
        if (authController.isLoggedIn()) {
            loginUserame = authController.getCurrentUser().getName();
            loginPassword = authController.getCurrentUser().getPassword();
        }
    }

    public AuthenticationController getAuthController() {
        return authController;
    }

    public void setAuthController(AuthenticationController authController) {
        this.authController = authController;
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
            authController.loginUser(data);
            PageController.redirectTo(PageController.userViewPage);
        } catch (Exception ex) {
            PageController.message("Error", "Login not possible");
        }
    }

    public void doLogout() {
        authController.logoutUser();
        PageController.redirectTo(PageController.userLoginPage);
    }

    public void doRegister() {
        UserData data = new UserData(registrationUsername, registrationPassword);

        try {
            authController.registerUser(data);
        } catch (Exception ex) {
            return;
        }

        PageController.redirectTo(PageController.userLoginPage);
    }
}
