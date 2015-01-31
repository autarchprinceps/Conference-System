package ooka.conference.viewControllers;

import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.entity.Publication;
import ooka.conference.ejb.SearchLocal;

@ManagedBean
@ViewScoped
public class PublicationSearchController {

    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;

    @EJB
    private SearchLocal searchEJB;

    private String searchTerm;

    private Collection<Publication> searchResults;

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void doSearch(String searchTerm) {
        //TODO
        searchResults = searchEJB.searchForPublications();
    }

    public Collection<Publication> getSearchResults() {
        return searchResults;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
