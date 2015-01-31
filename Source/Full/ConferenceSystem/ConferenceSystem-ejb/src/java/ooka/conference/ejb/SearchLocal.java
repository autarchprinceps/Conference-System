package ooka.conference.ejb;

import java.util.Collection;
import javax.ejb.Local;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;

@Local
public interface SearchLocal {

    public Collection<Publication> searchForPublications();

    public Collection<Conference> searchForConferences();
}
