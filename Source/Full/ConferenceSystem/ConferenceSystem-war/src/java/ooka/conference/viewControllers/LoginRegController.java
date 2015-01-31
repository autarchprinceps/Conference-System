package ooka.conference.viewControllers;

import ooka.conference.appControllers.ErrorController;
import ooka.conference.appControllers.PageController;
import javax.ejb.EJB;
import ooka.conference.util.Message;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.dto.UserData;
import ooka.conference.ejb.UserAdministrationLocal;

@ManagedBean
@ViewScoped
public class LoginRegController {

    @ManagedProperty(value = "#{errorController}")
    private ErrorController errorController;

    @ManagedProperty(value = "#{pageController}")
    private PageController pageController;

    @EJB
    private UserAdministrationLocal authEJB;
    
    private String registrationPassword;
    private String registrationUsername;
    private String loginPassword;
    private String loginUserame;

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

    public String doLogin() {

        UserData data = new UserData(loginUserame, loginPassword);
        boolean successful = authEJB.loginUser(data);

        if (successful) {
            errorController.setErrorMsg(null);
            return pageController.getIndexPage();
        } else {
            errorController.setErrorMsg(new Message("Login", "failure"));
            return pageController.getUserLoginPage();
        }
    }

    public String doRegister() {

        UserData data = new UserData(registrationUsername, registrationPassword);
        if (!authEJB.registerUser(data)) {
            errorController.setErrorMsg(new Message("Register", "failure"));
            return pageController.getErrorPage();
        }

        return pageController.getUserLoginPage();
    }
}
