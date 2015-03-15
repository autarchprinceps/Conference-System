package ooka.conference.ejb;

import java.util.Collection;
import java.util.stream.Collectors;
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

@Stateless
public class Search implements SearchLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Publication searchForPublication(Integer confId, Integer userId) {
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
    public Conference searchConferenceById(Integer pk) {
        Query searchQuery = em.createNamedQuery("Conference.findById");
        searchQuery.setParameter("id", pk);
        Conference conf = (Conference) searchQuery.getSingleResult();
        em.refresh(conf);
        return conf;
    }

    @Override
    public User searchUserById(Integer pk) {
        Query searchQuery = em.createNamedQuery("User.findById");
        searchQuery.setParameter("id", pk);
        return (User) searchQuery.getSingleResult();
    }

    @Override
    public Collection<ConferenceUserRole> searchConferencesForUser(Integer userId) {
        Query searchQuery = em.createNamedQuery("ConferenceUserRole.findByUserId");
        searchQuery.setParameter("userId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<ConferenceUserRole> searchUsersForConference(Integer confId) {
        Query searchQuery = em.createNamedQuery("ConferenceUserRole.findByConferenceId");
        searchQuery.setParameter("conferenceId", confId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Publication> searchPublicationsForUser(Integer userId) {
        Query searchQuery = em.createNamedQuery("Publication.findByAuthorId");
        searchQuery.setParameter("authorId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<PublicationReview> searchReviewsForUser(Integer userId) {
        Query searchQuery = em.createNamedQuery("PublicationReview.findByUserId");
        searchQuery.setParameter("reviewerId", userId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<PublicationReview> searchReviewsForPublication(int authorId, int conferenceId) {
        Query searchQuery = em.createNamedQuery("PublicationReview.findByPublication");
        searchQuery.setParameter("authorId", authorId);
        searchQuery.setParameter("conferenceId", conferenceId);
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Publication> searchPublicationsByTitleStartingWith(final String searchTerm) {
        // Query searchQuery = em.createNamedQuery("Publication.searchTitleStartingWith");
        // searchQuery.setParameter("title", searchTerm + '%');
        // return searchQuery.getResultList();
        
        // TODO case insensitive
        return searchForPublications().stream().filter((pub) -> pub.getTitle().toLowerCase().startsWith(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Collection<Conference> searchConferencesByNameStartingWith(final String searchTerm) {
        // Query searchQuery = em.createNamedQuery("Conference.searchNameStartingWith");
        // searchQuery.setParameter("name", searchTerm + '%');
        // return searchQuery.getResultList();
        
        // TODO case insensitive
        return searchForConferences().stream().filter((conf) -> conf.getName().toLowerCase().startsWith(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public User searchOrganizerForConference(final int conferenceId) {
        return searchUsersForConference(conferenceId).stream().filter((user) -> user.getUserRole().equalsIgnoreCase(Role.ORGANIZER.toString())).findAny().get().getUser();
    }

    @Override
    public int getAverageRatingOfConference(final Conference conference) {
        Collection<ConferenceRating> confRatings = conference.getConferenceRatingCollection(); 
        
        int ratingCount = confRatings.size();
        if (ratingCount > 0) {
            int sum = 0;
            for(ConferenceRating confRa : confRatings) {
                sum += confRa.getRating();
            }
            return Math.round(sum / ratingCount);
        } else {
            return 0;
        }
    }

    @Override
    public Collection<Conference> searchConferencesOrganizedBy(final int organizerId) {
        return searchForConferences().stream().filter((conf) -> searchOrganizerForConference(conf.getId()).getId() == organizerId).collect(Collectors.toList());
    }

    @Override
    public int getAverageRatingOfOrganizer(final int organizerId) {
        int sum = 0;
        int count = 0;
        for(Conference conf : searchConferencesOrganizedBy(organizerId)) {
            Collection<ConferenceRating> ratings = conf.getConferenceRatingCollection();
            count += ratings.size();
            for(ConferenceRating rating : ratings) {
                sum += rating.getRating();
            }
        }
        if(count > 0) {
            return Math.round(sum / count);
        } else {
            return 0;
        }
    }
    
    
}
