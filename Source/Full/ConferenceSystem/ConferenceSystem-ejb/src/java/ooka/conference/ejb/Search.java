package ooka.conference.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class Search implements SearchLocal {

        @PersistenceContext
        private EntityManager em;
    
	@Override
	public List<String> getConferenceAutocompletion(final String query) {
		return null;
	}

	@Override
	public List<String> getPubAutocomplete(final String query) {
		return null;
	}
	
	// TODO real search
}
