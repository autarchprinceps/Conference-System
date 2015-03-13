package ooka.conference.ejb;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationPK;
import ooka.conference.entity.PublicationRevision;
import ooka.conference.entity.PublicationRevisionPK;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.PublicationReviewPK;
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
    public void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception {
        PublicationRevision newRevision = new PublicationRevision();
        PublicationRevisionPK newRevisionPK = new PublicationRevisionPK();
        newRevisionPK.setAuthorId(authorId);
        newRevisionPK.setConferenceId(conferenceId);
        newRevision.setContent(content);
        newRevision.setDate(date);
        newRevision.setFileName(fileName);
        newRevision.setContentType(contentType);
        newRevision.setPublicationRevisionPK(newRevisionPK);
        em.persist(newRevision);
    }

    @Override
    public void reviewPublication(int reviewerId, int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception {
        PublicationReviewPK newReviewPK = new PublicationReviewPK(reviewerId, authorId, conferenceId);
        PublicationReview newReview = new PublicationReview(newReviewPK);
        newReview.setContent(content);
        newReview.setDate(date);
        newReview.setContentType(contentType);
        newReview.setFileName(fileName);
        newReview.setConference(em.find(Conference.class, conferenceId));
        newReview.setPub_author(em.find(User.class, reviewerId));
        em.persist(newReview);
    }

}
