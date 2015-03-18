package ooka.conference.ejb;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.dto.Role;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceRating;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.User;

@PermitAll
@Stateless
public class Search implements SearchLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Publication searchForPublication(int confId, int userId) {
        Query searchQuery = em.createNamedQuery("Publication.findByConferenceIdAndAuthorId");
        searchQuery.setParameter("conferenceId", confId);
        searchQuery.setParameter("authorId", userId);
        try {
            return (Publication) searchQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<Conference> searchForConferences() {
        Query searchQuery = em.createNamedQuery("Conference.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Publication> searchForPublications() {
        Query searchQuery = em.createNamedQuery("Publication.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public Collection<User> searchForUsers() {
        Query searchQuery = em.createNamedQuery("User.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public Conference searchConferenceById(int confId) {
        Query searchQuery = em.createNamedQuery("Conference.findById");
        searchQuery.setParameter("id", confId);
        Conference conf = (Conference) searchQuery.getSingleResult();
        return conf;
    }

    @Override
    public User searchUserById(int userId) {
        Query searchQuery = em.createNamedQuery("User.findById");
        searchQuery.setParameter("id", userId);
        return (User) searchQuery.getSingleResult();
    }

    @Override
    public Collection<ConferenceUserRole> searchConferencesForUser(int userId) {
        Query searchQuery = em.createNamedQuery("ConferenceUserRole.findByUserId");
        searchQuery.setParameter("userId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<ConferenceUserRole> searchUsersForConference(int confId) {
        Query searchQuery = em.createNamedQuery("ConferenceUserRole.findByConferenceId");
        searchQuery.setParameter("conferenceId", confId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Publication> searchPublicationsForUser(int userId) {
        Query searchQuery = em.createNamedQuery("Publication.findByAuthorId");
        searchQuery.setParameter("authorId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<PublicationReview> searchReviewsForUser(int userId) {
        Query searchQuery = em.createNamedQuery("PublicationReview.findByUserId");
        searchQuery.setParameter("reviewerId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<PublicationReview> searchReviewsForPublication(int userId, int conId) {
        Query searchQuery = em.createNamedQuery("PublicationReview.findByPublication");
        searchQuery.setParameter("authorId", userId);
        searchQuery.setParameter("conferenceId", conId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Publication> searchPublicationsByTitleStartingWith(String searchTerm) {
        return searchForPublications().stream().filter((pub) -> pub.getTitle().toLowerCase().startsWith(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Collection<Conference> searchConferencesByNameStartingWith(String searchTerm) {
        return searchForConferences().stream().filter((conf) -> conf.getName().toLowerCase().startsWith(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public User searchOrganizerForConference(int conferenceId) {
        return searchUsersForConference(conferenceId).stream().filter((user) -> user.getUserRole().equalsIgnoreCase(Role.ORGANIZER.toString())).findAny().get().getUser();
    }

    @Override
    public int getAverageRatingOfConference(int confId) {
        Collection<ConferenceRating> confRatings = searchConferenceById(confId).getConferenceRatingCollection();

        int ratingCount = confRatings.size();
        if (ratingCount > 0) {
            int sum = 0;
            for (ConferenceRating confRa : confRatings) {
                sum += confRa.getRating();
            }
            return Math.round(sum / ratingCount);
        } else {
            return 0;
        }
    }

    @Override
    public Collection<Conference> searchConferencesOrganizedBy(int userId) {
        return searchForConferences().stream().filter((conf) -> searchOrganizerForConference(conf.getId()).getId() == userId).collect(Collectors.toList());
    }

    @Override
    public int getAverageRatingOfOrganizer(int userId) {
        int sum = 0;
        int count = 0;
        for (Conference conf : searchConferencesOrganizedBy(userId)) {
            Collection<ConferenceRating> ratings = conf.getConferenceRatingCollection();
            count += ratings.size();
            for (ConferenceRating rating : ratings) {
                sum += rating.getRating();
            }
        }
        if (count > 0) {
            return Math.round(sum / count);
        } else {
            return 0;
        }
    }

}
