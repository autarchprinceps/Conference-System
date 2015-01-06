package ooka.conference.viewControllers;

import ooka.conference.dto.Conference;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ConferenceSearchController {
    public List<String> doAutocomplete(String query) {
        // TODO
        return new ArrayList<>();
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
