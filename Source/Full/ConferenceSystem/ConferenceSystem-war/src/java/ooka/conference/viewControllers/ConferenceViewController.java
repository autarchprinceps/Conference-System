package ooka.conference.viewControllers;

import ooka.conference.dto.Role;
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
import ooka.conference.appControllers.PageController;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceRating;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.User;
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

    private boolean currentUserIsRegistered;

    private String currentUserRole;

    private Conference currentConference;

    private ConferenceRating currentConferenceRating;

    private int averageConferenceRating;

    private Role selectedRoleForRegistration;

    @PostConstruct
    public void init() {

        String confId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confId");
        currentConference = searchEJB.searchConferenceById(Integer.parseInt(confId));

        User currentUser = authEJB.getCurrentUser();
        for (ConferenceUserRole role : currentConference.getConferenceUserRoleCollection()) {
            if (role.getUser().equals(currentUser)) {
                currentUserIsRegistered = true;
                currentUserRole = (String) role.getUserRole();
                break;
            }
        }

        for (ConferenceRating rating : currentConference.getConferenceRatingCollection()) {
            if (rating.getUser().equals(currentUser)) {
                averageConferenceRating = rating.getRating();
                currentConferenceRating = rating;
                break;
            }
        }

        int ratingCount = currentConference.getConferenceRatingCollection().size();
        if (ratingCount > 0) {
            averageConferenceRating = Math.round(averageConferenceRating / ratingCount);
            averageConferenceRating = averageConferenceRating > 6 ? 6 : averageConferenceRating;
        } else {
            averageConferenceRating = 0;
        }

    }

    public Role[] getRolesForSelection() {
        return Role.getParticipantRoles();
    }

    public void doDeregister() {
        try {
            confAdminEJB.deregisterToConference(currentConference.getId(), authEJB.getCurrentUser().getId());
        } catch (Exception ex) {
            Logger.getLogger(ConferenceViewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            PageController.redirectTo(PageController.userViewPage);
        }
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

        }
    }

    public void  doCancel() {
        try {
            confAdminEJB.cancelConference(currentConference.getId());
        } catch (Exception ex) {
            Logger.getLogger(ConferenceViewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            PageController.redirectTo(PageController.userViewPage);
        }

    }

    public int getAverageConferenceRating() {
        return averageConferenceRating;
    }

    public int getCurrentUsersRating() {
        if (currentConferenceRating == null) {
            return 0;
        } else {
            return currentConferenceRating.getRating();
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

    public String getCurrentUserRole() {
        return currentUserRole;
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
