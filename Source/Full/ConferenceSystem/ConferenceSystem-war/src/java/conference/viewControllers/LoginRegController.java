package conference.viewControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LoginRegController {
    
    @ManagedProperty(value="#{errorController}")
    private ErrorController errorController;
    
    @ManagedProperty(value="#{pageController}")
    private PageController pageController;

    public PageController getPageController() {
        return pageController;
    }

    public void setPageController(PageController pageController) {
        this.pageController = pageController;
    }
    
    private String userNameLogin;

    public String getUserNameLogin() {
        return userNameLogin;
    }

    public void setUserNameLogin(String userNameLogin) {
        this.userNameLogin = userNameLogin;
    }

    
    private String passwordLogin;

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

        private String regUserName;

    public String getRegUserName() {
        return regUserName;
    }

    public void setRegUserName(String regUserName) {
        this.regUserName = regUserName;
    }

    private String regPass;

    public String getRegPass() {
        return regPass;
    }

    public void setRegPass(String regPass) {
        this.regPass = regPass;
    }

    private String regPassRepeat;

    public String getRegPassRepeat() {
        return regPassRepeat;
    }

    public void setRegPassRepeat(String regPassRepeat) {
        this.regPassRepeat = regPassRepeat;
    }

    /**
     * @return the errorController
     */
    public ErrorController getErrorController() {
        return errorController;
    }

    /**
     * @param errorController the errorController to set
     */
    public void setErrorController(ErrorController errorController) {
        this.errorController = errorController;
    }

    
    public String doLogin() {
        //TODO login
        if(true) {
            return pageController.getIndex();
        } else {
            errorController.setErrorMsg(new Message("TODO", "TODO"));
            return pageController.getError();
        }
    }
    
    public String doRegister() {
        //TODO reg + login
        if(true) {
            return pageController.getIndex();
        } else {
            errorController.setErrorMsg(new Message("TODO", "TODO"));
            return pageController.getError();
        }
    }
}
