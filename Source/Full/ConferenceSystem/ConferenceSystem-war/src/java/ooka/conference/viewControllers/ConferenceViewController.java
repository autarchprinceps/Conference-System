package ooka.conference.viewControllers;

import java.util.Collection;
import ooka.conference.dto.Role;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    private boolean currentUserAlreadyCreatedPublication;

    private boolean currentUserAlreadyRated;

    private int averageConferenceRating;

    private Role selectedRoleForRegistration;

    @PostConstruct
    public void init() {

        String confId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confId");
        currentConference = searchEJB.searchConferenceById(Integer.parseInt(confId));

        // ROLES
        User currentUser = authEJB.getCurrentUser();
        for (ConferenceUserRole role : currentConference.getConferenceUserRoleCollection()) {
            if (role.getUser().equals(currentUser)) {
                currentUserIsRegistered = true;
                currentUserRole = (String) role.getUserRole();
                break;
            }
        }
        // HAS CURRENT USER ALREADY A PUBLICATION?
        if (Role.AUTHOR.toString().equals(currentUserRole)) {
            currentUserAlreadyCreatedPublication = searchEJB.searchForPublication(currentConference.getId(), currentUser.getId()) != null;
        }

        // RATING
        Collection<ConferenceRating> confRatings = currentConference.getConferenceRatingCollection();

        for (ConferenceRating confRating : confRatings) {
            if (confRating.getUser().equals(currentUser)) {
                currentConferenceRating = confRating;
                currentUserAlreadyRated = true;
                break;
            }
        }
        if (this.currentConferenceRating == null) {
            currentUserAlreadyRated = false;
            this.currentConferenceRating = new ConferenceRating(currentUser.getId(), currentConference.getId());
        }

        int ratingCount = confRatings.size();
        if (ratingCount > 0) {
            averageConferenceRating = Math.round(confRatings.stream().map((conferenceRating) -> conferenceRating.getRating()).reduce((a, b) -> a + b).orElse(ratingCount * 3) / ratingCount);
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
            PageController.redirectTo(PageController.userViewPage);
        } catch (Exception ex) {
            PageController.message("Deregister Error");
        }
    }

    public void doRegister() {

        try {
            confAdminEJB.registerToConference(currentConference.getId(), authEJB.getCurrentUser().getId(), selectedRoleForRegistration);
            PageController.redirectTo(PageController.confViewPage, "confId", currentConference.getId().toString());
        } catch (Exception ex) {
            PageController.message("Registration Error");
        }

    }

    public void doRate(RateEvent event) {
        try {
            confAdminEJB.rateConference(currentConference.getId(), authEJB.getCurrentUser().getId(), ((Integer) (event.getRating())));
            currentUserAlreadyRated = true;
            PageController.message("Rated with " + ((Integer) (event.getRating())) + " stars");
        } catch (Exception ex) {
            PageController.message("Rating Error");
        }
    }

    public void doCancel() {
        try {
            confAdminEJB.cancelConference(currentConference.getId());
        } catch (Exception ex) {
            PageController.message("Could not cancel the conference...");
        } finally {
            PageController.redirectTo(PageController.userViewPage);
        }
    }

    public boolean currentUserAlreadyRated() {
        return currentUserAlreadyRated;
    }

    public int getAverageConferenceRating() {
        return averageConferenceRating;
    }

    public int getCurrentConferenceRating() {
        if (currentConferenceRating == null) {
            return 0;
        } else {
            return currentConferenceRating.getRating();
        }
    }

    public void setCurrentConferenceRating(int currentConferenceRating) {
        this.currentConferenceRating.setRating(currentConferenceRating);
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

    public boolean isCurrentUserAlreadyCreatedPublication() {
        return currentUserAlreadyCreatedPublication;
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
