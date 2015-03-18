package ooka.conference.ejb;

import java.util.Date;
import javax.annotation.security.PermitAll;
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

@PermitAll
@Stateless
public class PublicationAdministration implements PublicationAdministrationLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPublication(int authorId, int conferenceId, String title) {
        PublicationPK pubPK = new PublicationPK();
        pubPK.setAuthorId(authorId);
        pubPK.setConferenceId(conferenceId);

        Publication newPublication = new Publication();
        newPublication.setTitle(title);
        newPublication.setPublicationPK(pubPK);
        em.persist(newPublication);
    }

    @Override
    public void deletePublication(int authorId, int conferenceId) {
        PublicationPK pubKey = new PublicationPK(conferenceId, authorId);
        em.remove(em.find(Publication.class, pubKey));
    }

    @Override
    public void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception {
        PublicationRevisionPK newRevisionPK = new PublicationRevisionPK();
        newRevisionPK.setAuthorId(authorId);
        newRevisionPK.setConferenceId(conferenceId);

        PublicationRevision newRevision = new PublicationRevision();
        newRevision.setContent(content);
        newRevision.setDate(date);
        newRevision.setFileName(fileName);
        newRevision.setContentType(contentType);
        newRevision.setPublicationRevisionPK(newRevisionPK);
        em.persist(newRevision);
    }

    @Override
    public void reviewPublication(int reviewerId, int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception {
        PublicationReviewPK reviewPK = new PublicationReviewPK(reviewerId, authorId, conferenceId);
        PublicationReview review = em.find(PublicationReview.class, reviewPK);
        review.setContent(content);
        review.setDate(date);
        review.setFileName(fileName);
        review.setContentType(contentType);
        em.merge(review);
    }

    @Override
    public void addReviewerToPublication(int reviewerId, int authorId, int conferenceId) throws Exception {
        PublicationReviewPK newReviewPK = new PublicationReviewPK(reviewerId, authorId, conferenceId);
        PublicationReview newReview = new PublicationReview(newReviewPK);
        newReview.setConference(em.find(Conference.class, conferenceId));
        newReview.setPub_author(em.find(User.class, reviewerId));
        em.persist(newReview);
    }

}
