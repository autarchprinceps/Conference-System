package ooka.conference.viewControllers;

import java.util.Collection;
import ooka.conference.dto.Role;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.appControllers.PageController;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceRating;
import org.primefaces.event.RateEvent;

@ManagedBean
@ViewScoped
public class ConferenceViewController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authController;

    @EJB
    private ConferenceAdministrationLocal confAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    private boolean currentUserIsRegistered;

    private String currentUserRole;

    private Conference currentConference;

    private int currentConferenceRating;

    private boolean currentUserAlreadyCreatedPublication;

    private boolean currentUserAlreadyRated;

    private int averageConferenceRating;

    private Role selectedRoleForRegistration;

    @PostConstruct
    public void init() {
        int confId = PageController.getParamToInt("confId");

        currentConference = searchEJB.searchConferenceById(confId);
        currentUserRole = authController.getRoleForConference(currentConference);
        currentUserIsRegistered = currentUserRole != null;

        if (currentUserIsRegistered) {

            findCurrentUserRating();

            // HAS THE USER ALREADY CREATED A PUBLICATION?
            if (currentUserRole.equals(Role.AUTHOR.toString())) {
                currentUserAlreadyCreatedPublication = searchEJB.searchForPublication(confId, authController.getCurrentUser().getId()) != null;
            }
        }

        // CALCULATE RATINGS
        Collection<ConferenceRating> confRatings = currentConference.getConferenceRatingCollection();
        int ratingCount = confRatings.size();
        if (ratingCount > 0) {
            // TODO irgendwie bekomme ich ne exception
            // averageConferenceRating = Math.round(confRatings.stream().map((conferenceRating) -> conferenceRating.getRating()).reduce((a, b) -> a + b).get() / ratingCount) + 3;
            averageConferenceRating = 0;
        } else {
            averageConferenceRating = 0;
        }
    }

    private void findCurrentUserRating() {
        currentUserAlreadyRated = false;
        for (ConferenceRating confRating : currentConference.getConferenceRatingCollection()) {
            if (confRating.getUser().equals(authController.getCurrentUser())) {
                currentConferenceRating = confRating.getRating() + 3;
                currentUserAlreadyRated = true;
                break;
            }
        }
    }

    public void doDeregister() {
        try {
            confAdminEJB.deregisterToConference(currentConference.getId(), authController.getCurrentUser().getId());
            PageController.redirectTo(PageController.userViewPage);
        } catch (Exception ex) {
            PageController.message("Error", "Could not deregister from conference");
        }
    }

    public void doRegister() {

        try {
            confAdminEJB.registerToConference(currentConference.getId(), authController.getCurrentUser().getId(), selectedRoleForRegistration);
            PageController.redirectTo(PageController.confViewPage, "confId", currentConference.getId().toString());
        } catch (Exception ex) {
            PageController.message("Error", "Could not register to conferene");
        }

    }

    public void doRate(RateEvent event) {
        try {
            confAdminEJB.rateConference(currentConference.getId(), authController.getCurrentUser().getId(), (currentConferenceRating - 3));
            currentUserAlreadyRated = true;
            PageController.message("Rating done", "Rated with " + currentConferenceRating + " stars");
        } catch (Exception ex) {
            PageController.message("Error", "Could not rate the conference");
        }
    }

    public void doCancel() {
        try {
            confAdminEJB.cancelConference(currentConference.getId());
        } catch (Exception ex) {
            PageController.message("Error", "Could not cancel the conference...");
        } finally {
            PageController.redirectTo(PageController.userViewPage);
        }
    }

    public Role[] getRolesForSelection() {
        return Role.getParticipantRoles();
    }

    public boolean currentUserAlreadyRated() {
        return currentUserAlreadyRated;
    }

    public int getAverageConferenceRating() {
        return averageConferenceRating;
    }

    public int getCurrentConferenceRating() {
        if (currentUserAlreadyRated) {
            return currentConferenceRating;
        } else {
            return 0;
        }
    }

    public void setCurrentConferenceRating(int currentConferenceRating) {
        this.currentConferenceRating = currentConferenceRating;
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

    public AuthenticationController getAuthController() {
        return authController;
    }

    public boolean isCurrentUserAlreadyCreatedPublication() {
        return currentUserAlreadyCreatedPublication;
    }

    public void setAuthController(AuthenticationController authController) {
        this.authController = authController;
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
