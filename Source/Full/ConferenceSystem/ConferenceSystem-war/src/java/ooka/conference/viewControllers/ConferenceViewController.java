package ooka.conference.viewControllers;

import ooka.conference.dto.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.User;
import ooka.conference.util.NotLoggedInException;
import org.primefaces.event.RateEvent;

@ManagedBean
@ViewScoped
public class ConferenceViewController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    @EJB
    private ConferenceAdministrationLocal confAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    private Role selectedRoleForRegistration;

    private Conference currentConference;

    private boolean currentUserIsRegistered;

    @PostConstruct
    public void init() {
        String confId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confId");
        currentConference = searchEJB.searchConferenceById(Integer.parseInt(confId));

        User currentUser = null;
        try {
            currentUser = authEJB.getCurrentUser();
        } catch (NotLoggedInException ex) {
            Logger.getLogger(ConferenceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ConferenceUserRole role : currentConference.getConferenceUserRoleCollection()) {
            if (role.getUser().equals(currentUser)) {
                currentUserIsRegistered = true;
                break;
            }
        }
    }


    /*
     public List<UserConferenceConnection> getUsers() {
     // TODO get from jpa
     List<UserConferenceConnection> result = new ArrayList<>();
     result.add(new UserConferenceConnection(0, "Max Muster", 0, "conf", Role.AUTHOR));
     return result;
     }
     */
    public Role[] getRolesForSelection() {
        return Role.getParticipantRoles();
    }

    public void doDeregister() {
        // TODO getCurrentUserId from authentication
        // TODO do deregister in model
        // TODO + getError
        // TODO refresh
        if (true /* successful deregister*/) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deregistered from conference " + currentConference.getName(), null)
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to deregistered from conference", null)
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

        try {
            confAdminEJB.registerToConference(currentConference.getId(), authEJB.getCurrentUser().getId(), selectedRoleForRegistration);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while trying to register to conference", null)
                    );
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully registered to conference " + currentConference.getName() + " with role " + selectedRoleForRegistration, null)
        );
    }

    public void doRate(RateEvent event) {
        try {
            confAdminEJB.rateConference(currentConference.getId(), authEJB.getCurrentUser().getId(), ((Integer) (event.getRating())) - 6);
        } catch (Exception ex) {
            // TODO
        }
    }

    public Role getSelectedRoleForRegistration() {
        return selectedRoleForRegistration;
    }

    public void setSelectedRoleForRegistration(Role selectedRoleForRegistration) {
        this.selectedRoleForRegistration = selectedRoleForRegistration;
    }

    public Conference getCurrentConference() {
        return currentConference;
    }

    public boolean isCurrentUserIsRegistered() {
        return currentUserIsRegistered;
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    public ConferenceAdministrationLocal getConfAdminEJB() {
        return confAdminEJB;
    }

    public void setConfAdminEJB(ConferenceAdministrationLocal confAdminEJB) {
        this.confAdminEJB = confAdminEJB;
    }

    public SearchLocal getSearchEJB() {
        return searchEJB;
    }

    public void setSearchEJB(SearchLocal searchEJB) {
        this.searchEJB = searchEJB;
    }

}
