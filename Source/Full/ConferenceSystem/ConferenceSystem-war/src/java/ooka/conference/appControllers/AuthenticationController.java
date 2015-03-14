package ooka.conference.appControllers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.conference.dto.Role;
import ooka.conference.dto.UserData;
import ooka.conference.ejb.UserAdministrationLocal;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.User;
import ooka.conference.util.WrongLoginCredentialsException;

@ManagedBean
@SessionScoped
public class AuthenticationController implements Serializable {

    @EJB
    private UserAdministrationLocal userEJB;

    private User currentUser;

    public void loginUser(UserData data) throws Exception {

        if (isLoggedIn()) {
            throw new Exception("Already logged in!");
        }

        try {
            currentUser = userEJB.validateUser(data);
        } catch (Exception e) {
            currentUser = null;
            throw new WrongLoginCredentialsException();
        }

        if (currentUser == null) {
            throw new WrongLoginCredentialsException();
        }

    }

    public void logoutUser() {
        currentUser = null;
        PageController.redirectTo(PageController.userLoginPage);
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        if (!isLoggedIn()) {
            // force to login
            PageController.redirectTo(PageController.userLoginPage);
            return null;
        } else {
            return currentUser;
        }
    }

    public void registerUser(UserData data) throws Exception {
        userEJB.registerUser(data);
    }

    public String getRoleForConference(Conference conf) {
        for (ConferenceUserRole role : conf.getConferenceUserRoleCollection()) {
            if (role.getUser().equals(currentUser)) {
                return role.getUserRole();
            }
        }
        return null;
    }

    public boolean isRegisteredOnConference(Conference conf) {
        for (ConferenceUserRole role : conf.getConferenceUserRoleCollection()) {
            if (role.getUser().equals(currentUser)) {
                return true;
            }
        }

        return false;
    }

    public boolean isReviewerOfConference(Conference conf) {
        return checkForRole(conf, Role.REVIEWER);
    }

    public boolean isOrganizerOfConference(Conference conf) {
        return checkForRole(conf, Role.ORGANIZER);
    }

    public boolean isViewerOfConference(Conference conf) {
        return checkForRole(conf, Role.VIEWER);
    }

    public boolean isAuthorOfConference(Conference conf) {
        return checkForRole(conf, Role.AUTHOR);
    }

    public boolean isReviewerOfPublication(Publication pub) {
        for (PublicationReview review : pub.getReviews()) {
            if (review.getReview_author().equals(currentUser)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthorOfPublication(Publication pub) {
        return pub.getUser().equals(currentUser);
    }

    private boolean checkForRole(Conference conf, Role role) {

        if (!isLoggedIn()) {
            return false;
        }

        for (ConferenceUserRole crole : conf.getConferenceUserRoleCollection()) {
            if (crole.getUserRole().equals(role.toString()) && crole.getUser().equals(currentUser)) {
                return true;
            } else {
                return false;
            }
        }

        // return conf.getConferenceUserRoleCollection().stream().filter((role) -> (role.getUser().equals(currentUser))).anyMatch((role) -> (role.getUserRole().equalsIgnoreCase(Role.REVIEWER.toString())));
        return false;
    }
}
