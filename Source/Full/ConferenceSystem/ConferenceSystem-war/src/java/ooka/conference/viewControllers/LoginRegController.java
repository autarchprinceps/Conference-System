package ooka.conference.viewControllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import ooka.conference.appControllers.ErrorController;
import ooka.conference.appControllers.PageController;
import ooka.conference.util.Message;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.dto.UserData;
import ooka.conference.util.WrongLoginCredentialsException;

@ManagedBean
@ViewScoped
public class LoginRegController {

    @ManagedProperty(value = "#{errorController}")
    private ErrorController errorController;

    @ManagedProperty(value = "#{pageController}")
    private PageController pageController;

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

    public PageController getPageController() {
        return pageController;
    }

    public void setPageController(PageController pageController) {
        this.pageController = pageController;
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

    public ErrorController getErrorController() {
        return errorController;
    }

    public void setErrorController(ErrorController errorController) {
        this.errorController = errorController;
    }

    public void doLogin() {

        UserData data = new UserData(loginUserame, loginPassword);
        try {
            authEJB.loginUser(data);
        } catch (WrongLoginCredentialsException ex) {
            Logger.getLogger(LoginRegController.class.getName()).log(Level.SEVERE, null, ex);
            errorController.setErrorMsg(new Message("Login", "failure"));
            PageController.redirectTo(pageController.getUserLoginPage());
        }

        errorController.setErrorMsg(null);
        PageController.redirectTo(PageController.userViewPage);
    }

    public void doLogout() {
        authEJB.logoutUser();
        PageController.redirectTo(PageController.userLoginPage);
    }

    public void doRegister() {

        UserData data = new UserData(registrationUsername, registrationPassword);
        if (!authEJB.registerUser(data)) {
            errorController.setErrorMsg(new Message("Register", "failure"));
        }

        PageController.redirectTo(PageController.userLoginPage);
    }
}
