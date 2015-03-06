package ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationPK;
import ooka.conference.entity.PublicationRevision;
import ooka.conference.entity.PublicationRevisionPK;
import ooka.conference.entity.User;

@Stateless
public class PublicationAdministration implements PublicationAdministrationLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public void createPublication(int authorId, int conferenceId, String title) {
        Publication newPublication = new Publication();
        newPublication.setTitle(title);
        newPublication.setUser(em.find(User.class, authorId));
        newPublication.setConference(em.find(Conference.class, conferenceId));

        PublicationPK association_pk = new PublicationPK();
        association_pk.setAuthorId(authorId);
        association_pk.setConferenceId(conferenceId);

        newPublication.setPublicationPK(association_pk);
        em.persist(newPublication);
    }

    @Override
    public void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType) throws Exception {
        /*
         Query pubQuery = em.createNamedQuery("Publication.findByConferenceIdAndAuthorId");
         pubQuery.setParameter(":authorId", authorId);
         pubQuery.setParameter(":conferenceId", conferenceId);
         Publication publication = (Publication) pubQuery.getSingleResult();
         */

        PublicationRevision newRevision = new PublicationRevision();
        newRevision.setContent(content);
        PublicationRevisionPK newRevisionPK = new PublicationRevisionPK();
        newRevisionPK.setAuthorId(authorId);
        newRevisionPK.setConferenceId(conferenceId);
        newRevisionPK.setFileName(fileName);
        newRevisionPK.setContentType(contentType);
        newRevision.setPublicationRevisionPK(newRevisionPK);
        em.persist(newRevision);
    }

    /*
     @Override
     public void reviewPublication(int reviewerId, int authorId, int conferenceId, String content) throws Exception {
     Review newReview = new Review();
     newReview.setUser(em.find(User.class, authorId));
     newReview.setConference(em.find(Conference.class, conferenceId));
     newReview.setText(content);

     ReviewPK association_pk = new ReviewPK();
     association_pk.setAuthorId(authorId);
     association_pk.setConferenceId(conferenceId);

     newReview.setReviewPK(association_pk);

     em.persist(newReview);
     }
     */
}
