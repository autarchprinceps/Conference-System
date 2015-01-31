package ooka.conference.viewControllers;

import ooka.conference.dto.UserConferenceConnection;
import ooka.conference.dto.Role;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;

@ManagedBean
@ViewScoped
public class UserViewController {
    
    private int userIdGetParameter;

    public int getUserIdGetParameter() {
        return userIdGetParameter;
    }

    public void setUserIdGetParameter(int userIdGetParameter) {
        this.userIdGetParameter = userIdGetParameter;
    }

    public String getUserName() {
        // TODO get name from jpa entities by id
        return "Max Muster";
    }

    public List<UserConferenceConnection> getConferences() {
        // TODO get from jpa
        List<UserConferenceConnection> result = new ArrayList<>();
        result.add(new UserConferenceConnection(0, "Max Muster", 0, "conf", Role.AUTHOR));
        return result;
    }

    public List<ConferenceData> getOrganizedConferences() {
        // TODO
        return new ArrayList<>();
    }

    public boolean isAccount() {
        // TODO getCurrentUserId from authentication service
        return userIdGetParameter == 0;
    }

    public void doDeregister(int conferenceId) {
        // TODO refresh dataTable
        // TODO send action to managing ejb
        // TODO add + getConferenceName
        // TODO add + getError
        if (true /* successful deregister*/) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deregistered from conference", null)
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to deregistered from conference", null)
            );
        }
    }
}
