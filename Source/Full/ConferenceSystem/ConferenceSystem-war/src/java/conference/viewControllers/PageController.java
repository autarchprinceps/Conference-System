package conference.viewControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PageController {
    private final String conferenceView = "conferenceView";
    private final String loginReg = "loginReg";
    private final String pubView = "pubView";
    private final String userView = "userView";
    private final String conferenceSearch = "conferenceSearch";
    private final String pubSearch = "pubSearch";

    public String getConferenceView() {
        return conferenceView;
    }

    public String getLoginReg() {
        return loginReg;
    }

    public String getPubView() {
        return pubView;
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
