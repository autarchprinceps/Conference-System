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

    /*
     * CONFERENCE
     */
    Conference searchConferenceById(int confId);

    Collection<Conference> searchForConferences();

    Collection<Conference> searchConferencesOrganizedBy(int userId);

    Collection<Conference> searchConferencesByNameStartingWith(String searchTerm);

    Collection<ConferenceUserRole> searchConferencesForUser(int userId);

    Collection<ConferenceUserRole> searchUsersForConference(int confId);

    int getAverageRatingOfConference(int confId);

    /*
     * USER
     */
    Collection<User> searchForUsers();

    User searchUserById(int userId);

    User searchOrganizerForConference(int confId);

    int getAverageRatingOfOrganizer(int userId);

    /*
     * PUBLICATION
     */
    Publication searchForPublication(int confId, int userId);

    Collection<Publication> searchForPublications();

    Collection<Publication> searchPublicationsForUser(int userId);

    Collection<Publication> searchPublicationsByTitleStartingWith(String searchTerm);

    Collection<PublicationReview> searchReviewsForUser(int userId);

    Collection<PublicationReview> searchReviewsForPublication(int authorId, int confId);

}
