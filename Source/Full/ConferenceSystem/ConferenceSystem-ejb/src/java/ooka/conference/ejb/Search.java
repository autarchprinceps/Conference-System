package ooka.conference.ejb;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.User;

@Stateless
public class Search implements SearchLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Publication> searchForPublications() {
        Query searchQuery = em.createNamedQuery("Publication.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public Collection<Conference> searchForConferences() {
        Query searchQuery = em.createNamedQuery("Conference.findAll");
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
        return (Conference) searchQuery.getSingleResult();
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

}
