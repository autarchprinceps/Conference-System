package ooka.conference.ejb;

import java.util.Collection;
import javax.ejb.Local;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;
import ooka.conference.entity.User;

@Local
public interface SearchLocal {

    public Collection<Publication> searchForPublications();

    public Collection<Conference> searchForConferences();
    
    public Collection<User> searchForUsers();
    
    public User searchUserById(Integer pk);
}
