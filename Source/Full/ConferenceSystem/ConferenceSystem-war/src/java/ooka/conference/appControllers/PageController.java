package ooka.conference.appControllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import ooka.conference.viewControllers.LoginRegController;

@ManagedBean
@ApplicationScoped
public class PageController {

    public static final String errorPage = "error";
    public static final String confSearchPage = "confSearch";
    public static final String confViewPage = "confView";
    public static final String confCreatePage = "confCreate";
    public static final String userLoginPage = "userLogin";
    public static final String userRegisterPage = "userRegister";
    public static final String userViewPage = "userView";
    public static final String pubCreatePage = "pubCreate";
    public static final String pubViewPage = "pubView";
    public static final String pubSearchPage = "pubSearch";

    public static void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./" + page + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void redirectTo(String page, String paramName, String paramValue) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./" + page + ".xhtml?" + paramName + "=" + paramValue);
        } catch (IOException ex) {
            Logger.getLogger(LoginRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void reload() {
        // TODO FIX new 2
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getServletPath());
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getErrorPage() {
        return errorPage;
    }

    public String getConfCreatePage() {
        return confCreatePage;
    }

    public String getConfViewPage() {
        return confViewPage;
    }

    public String getUserLoginPage() {
        return userLoginPage;
    }

    public String getPubViewPage() {
        return pubViewPage;
    }

    public String getUserRegisterPage() {
        return userRegisterPage;
    }

    public String getUserViewPage() {
        return userViewPage;
    }

    public String getConfSearchPage() {
        return confSearchPage;
    }

    public String getPubSearchPage() {
        return pubSearchPage;
    }

    public String getPubCreatePage() {
        return pubCreatePage;
    }

}
