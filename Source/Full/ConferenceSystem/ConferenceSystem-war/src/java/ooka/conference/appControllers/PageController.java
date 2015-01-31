package ooka.conference.appControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PageController {

    private final String indexPage = "index";
    private final String errorPage = "error";
    private final String confSearchPage = "confSearch";
    private final String confViewPage = "confView";
    private final String confCreatePage = "confCreate";
    private final String userLoginPage = "userLogin";
    private final String userRegisterPage = "userRegister";
    private final String userViewPage = "userView";
    private final String pubViewPage = "pubView";
    private final String pubSearchPage = "pubSearch";

    public String getErrorPage() {
        return errorPage;
    }

    public String getIndexPage() {
        return indexPage;
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
}
