package ooka.conference.viewControllers;

import ooka.conference.entity.Conference;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.ejb.SearchLocal;

@ManagedBean
@ViewScoped
public class ConferenceSearchController {

    @EJB
    private SearchLocal searchEJB;

    private String searchTerm;

    private Collection<Conference> searchResults;

    public void doSearch(String query) {
        searchResults = searchEJB.searchForConferences();
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

}
