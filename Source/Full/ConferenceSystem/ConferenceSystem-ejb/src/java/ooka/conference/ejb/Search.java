package ooka.conference.ejb;

import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class Search implements SearchLocal {

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
