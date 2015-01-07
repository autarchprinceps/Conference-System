package ooka.conference.viewControllers;

import ooka.conference.dto.UserConferenceConnection;
import ooka.conference.dto.ParticipantRole;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import org.primefaces.event.RateEvent;

@ManagedBean
@ViewScoped
public class ConferenceViewController {
    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;
	
	@EJB
	private ConferenceAdministrationLocal conferenceAdministrationBean;
    
    private ParticipantRole selectedRoleForRegistration;
    
    public String getConferenceName() {
        // TODO get name from jpa
        return "conference";
    }
    
    public int getConferenceOrganizerId() {
        // TODO get from jpa
        return 0;
    }
    
    public String getConferenceOrganizerName() {
        // TODO get name from jpa
        return "Hans Muster";
    }
    
    public boolean isCurrentUserRegisteredToThis() {
        // TODO
        return false;
    }
    
    public List<UserConferenceConnection> getUsers() {
        // TODO get from jpa
        List<UserConferenceConnection> result = new ArrayList<>();
        result.add(new UserConferenceConnection(0, "Max Muster", 0, "conf", ParticipantRole.Author));
        return result;
    }
    
    public ParticipantRole[] getRolesForSelection() {
        return ParticipantRole.values();
    }
    
    public void doDeregister() {
        // TODO getCurrentUserId from authentication
        // TODO do deregister in model
        // TODO + getError
        // TODO refresh
        if(true /* successful deregister*/) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deregistered from conference " + getConferenceName() , null)
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to deregistered from conference" , null)
            );
        }
    }
	
	public int getOverallConferenceRating() {
		// TODO
		return 0 + 6;
	}
	
	public int getCurrentUsersRating() {
		// TODO
		return 0 + 6;
	}
    
    public void doRegister() {
        // TODO getCurrentUserId from authentication
        // TODO do deregister in model
        // TODO + getError
        // TODO refresh
        if(true /* successful deregister*/) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully registered to conference " + getConferenceName() + " with role " + selectedRoleForRegistration , null)
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to register to conference" , null)
            );
        }
    }
	
	public void doRate(RateEvent event) {
		// TODO get current userId from auth
		try {
			conferenceAdministrationBean.rateConference(conferenceId, 0, ((Integer)(event.getRating())) - 6);
		} catch(Exception ex) {
			// TODO
		}
	}

    public int getConferenceId() {
        return conferenceId;
    }

    public ParticipantRole getSelectedRoleForRegistration() {
        return selectedRoleForRegistration;
    }

    public void setSelectedRoleForRegistration(ParticipantRole selectedRoleForRegistration) {
        this.selectedRoleForRegistration = selectedRoleForRegistration;
    }
}
