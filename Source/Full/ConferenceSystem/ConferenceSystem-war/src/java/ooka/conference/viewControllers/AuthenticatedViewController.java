package ooka.conference.viewControllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.appControllers.PageController;

/**
 *
 * @author Bastian
 */
public abstract class AuthenticatedViewController {

    @ManagedProperty(value = "#{authenticationController}")
    protected AuthenticationController authController;

    @PostConstruct
    private void runPostConstructSetup() {
        if (!authController.isLoggedIn()) {
            PageController.redirectTo(PageController.userLoginPage);
        }
    }

    public AuthenticationController getAuthController() {
        return authController;
    }

    public void setAuthController(AuthenticationController authController) {
        this.authController = authController;
    }

}
