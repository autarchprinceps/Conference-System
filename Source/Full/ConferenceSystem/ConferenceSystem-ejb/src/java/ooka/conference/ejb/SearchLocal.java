package ooka.conference.ejb;

import java.util.Collection;
import javax.ejb.Local;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.User;

@Local
public interface SearchLocal {

    public Publication searchForPublication(Integer confId, Integer userId);

    public Collection<Conference> searchForConferences();

    public Collection<User> searchForUsers();

    public Conference searchConferenceById(Integer pk);

    public User searchUserById(Integer pk);

    public Collection<ConferenceUserRole> searchConferencesForUser(Integer userId);

    public Collection<Publication> searchPublicationsForUser(Integer userId);

    public Collection<PublicationReview> searchReviewsForUser(Integer userId);

    public Collection<ConferenceUserRole> searchUsersForConference(Integer confId);
    
    public Collection<PublicationReview> searchReviewsForPublication(int authorId, int conferenceId);
}
