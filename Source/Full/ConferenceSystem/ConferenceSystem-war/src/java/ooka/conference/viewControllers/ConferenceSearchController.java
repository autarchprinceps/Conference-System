package ooka.conference.viewControllers;

import ooka.conference.entity.Conference;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class ConferenceSearchController extends AuthenticatedViewController {

    @EJB
    private SearchLocal searchEJB;

    private String searchTerm;

    private Collection<Conference> searchResults;

    public void doSearch() {
        searchResults = searchEJB.searchConferencesByNameStartingWith(searchTerm);
    }

    public Collection<Conference> getSearchResults() {
        return searchResults;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public User getOrganizerOfConference(Conference conf) {
        return searchEJB.searchOrganizerForConference(conf.getId());
    }

    public int getAvgRating(Conference conf) {
        return searchEJB.getAverageRatingOfConference(conf) + 3;
    }
}
