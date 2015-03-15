package ooka.conference.viewControllers;

import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.entity.Publication;
import ooka.conference.ejb.SearchLocal;

@ManagedBean
@ViewScoped
public class PublicationSearchController {
    @EJB
    private SearchLocal searchEJB;

    private String searchTerm;

    private Collection<Publication> searchResults;

    public void doSearch() {
        searchResults = searchEJB.searchPublicationsByTitleStartingWith(searchTerm);
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
