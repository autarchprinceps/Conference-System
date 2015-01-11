package ooka.conference.viewControllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.dto.Conference;
import ooka.conference.dto.Publication;
import ooka.conference.ejb.SearchLocal;

@ManagedBean
@ViewScoped
public class PubSearchController {
    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;

    /**
     * @return the conferenceId
     */
    public int getConferenceId() {
        return conferenceId;
    }

    /**
     * @param conferenceId the conferenceId to set
     */
    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }
    
    @EJB
    private SearchLocal searchBean;
	
    public List<String> doAutocomplete(String query) {
        return searchBean.getConferenceAutocompletion(query);
    }

    public List<Publication> getSearchResults() {
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
