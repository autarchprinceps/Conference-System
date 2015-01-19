package ooka.conference.viewControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PageController {

    private final String conferenceView = "conferenceView";
    private final String login = "login";
    private final String register = "register";
    private final String pubView = "pubView";
    private final String userView = "userView";
    private final String conferenceSearch = "conferenceSearch";
    private final String pubSearch = "pubSearch";
    private final String index = "index";
    private final String error = "error";

    public String getError() {
        return error;
    }

    public String getIndex() {
        return index;
    }

    public String getConferenceView() {
        return conferenceView;
    }

    public String getLogin() {
        return login;
    }

    public String getPubView() {
        return pubView;
    }

    public String getRegister() {
        return register;
    }

    public String getUserView() {
        return userView;
    }

    public String getConferenceSearch() {
        return conferenceSearch;
    }

    public String getPubSearch() {
        return pubSearch;
    }
}
