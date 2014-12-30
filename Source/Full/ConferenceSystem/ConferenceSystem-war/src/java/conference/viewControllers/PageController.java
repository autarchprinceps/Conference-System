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
}
