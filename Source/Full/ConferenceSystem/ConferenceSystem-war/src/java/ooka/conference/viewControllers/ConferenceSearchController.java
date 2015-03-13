package ooka.conference.viewControllers;

import ooka.conference.entity.Conference;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.ConferenceRating;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class ConferenceSearchController {

    @EJB
    private SearchLocal searchEJB;

    private String searchTerm;

    private Collection<Conference> searchResults;

    public void doSearch(String query) {
        searchResults = searchEJB.searchConferencesByNameStartingWith(query);
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
        Collection<ConferenceRating> confRatings = conf.getConferenceRatingCollection(); 
        
        int ratingCount = confRatings.size();
        if (ratingCount > 0) {
            return Math.round(confRatings.stream().map((conferenceRating) -> conferenceRating.getRating()).reduce((a, b) -> a + b).get() / ratingCount) + 3;
        } else {
            return 3;
        }
    }
}
