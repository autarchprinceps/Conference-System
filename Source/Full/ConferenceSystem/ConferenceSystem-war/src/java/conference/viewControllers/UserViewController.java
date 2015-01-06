package conference.viewControllers;

import conference.dataTransferal.UserConferenceConnection;
import conference.dataTransferal.ParticipantRole;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UserViewController {
    
    @ManagedProperty(value = "#{param.userId}")
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
        result.add(new UserConferenceConnection(0, "Max Muster", 0, "conf", ParticipantRole.Author));
        return result;
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
        if(true /* successful deregister*/) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deregistered from conference" , null)
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to deregistered from conference" , null)
            );
        }
    }
}
