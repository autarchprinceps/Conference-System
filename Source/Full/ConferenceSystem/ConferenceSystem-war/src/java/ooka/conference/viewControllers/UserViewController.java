package ooka.conference.viewControllers;

import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class UserViewController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    @EJB
    private SearchLocal searchEJB;

    private User displayedUser;

    @PostConstruct
    public void init() {
        Map<String, String> context = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (context.containsKey("userId")) {
            String userId = (String) context.get("userId");
            displayedUser = searchEJB.searchUserById(Integer.parseInt(userId));
        } else {
            displayedUser = authEJB.getCurrentUser();
        }
    }

    public boolean viewingUserIsCurrentUser() {
        return displayedUser.equals(authEJB.getCurrentUser());
    }

    public Collection<ConferenceUserRole> getConferences() {
        // search to get the most recent data
        return searchEJB.searchConferencesForUser(displayedUser.getId());
    }

    public Collection<Publication> getPublications() {
        // search to get the most recent data
        return searchEJB.searchPublicationsForUser(displayedUser.getId());
    }

    public Collection<PublicationReview> getReviews() {
        // search to get the most recent data
        return searchEJB.searchReviewsForUser(displayedUser.getId());
    }
    
    public String getConferenceNameById(int confId) {
        return searchEJB.searchConferenceById(confId).getName();
    }
    
    public String getPublicationTitleByReview(PublicationReview review) {
        return searchEJB.searchForPublication(review.getReviewPK().getConferenceId(), review.getReviewPK().getAuthorId()).getTitle();
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    public SearchLocal getSearchEJB() {
        return searchEJB;
    }

    public void setSearchEJB(SearchLocal searchEJB) {
        this.searchEJB = searchEJB;
    }

    public User getDisplayedUser() {
        return displayedUser;
    }

}
