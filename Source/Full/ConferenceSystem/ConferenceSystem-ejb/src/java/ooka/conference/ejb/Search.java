package ooka.conference.ejb;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;

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
}
