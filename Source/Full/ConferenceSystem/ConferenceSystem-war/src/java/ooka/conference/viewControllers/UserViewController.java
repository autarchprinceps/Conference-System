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
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class UserViewController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    @EJB
    private SearchLocal searchEJB;

    private User currentUser;

    @PostConstruct
    public void init() {
        Map<String, String> context = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (context.containsKey("userId")) {
            String userId = (String) context.get("userId");
            currentUser = searchEJB.searchUserById(Integer.parseInt(userId));
        } else {
            currentUser = authEJB.getCurrentUser();
        }
    }

    public boolean viewingUserIsCurrentUser() {
        return currentUser.equals(authEJB.getCurrentUser());
    }

    public Collection<ConferenceUserRole> getConferences() {
        // search to get the most recent data
        return searchEJB.searchConferencesForUser(currentUser.getId());
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

    public User getCurrentUser() {
        return currentUser;
    }

}
