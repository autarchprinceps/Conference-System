package ooka.conference.viewControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PubSearchController {
    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;

    /**
     * @return the conferenceId
     */
    public int getConferenceId() {
        return conferenceId;
    }

    /**
     * @param conferenceId the conferenceId to set
     */
    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }
}
