package ooka.conference.viewControllers;

import ooka.conference.dto.Conference;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.ejb.SearchLocal;

@ManagedBean
@ViewScoped
public class ConferenceSearchController {
	@EJB
	private SearchLocal searchBean;
	
    public List<String> doAutocomplete(String query) {
		return searchBean.getConferenceAutocompletion(query);
    }

    public List<Conference> getSearchResults() {
        // TODO do search
        return new ArrayList<>();
    }
    
    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


}
