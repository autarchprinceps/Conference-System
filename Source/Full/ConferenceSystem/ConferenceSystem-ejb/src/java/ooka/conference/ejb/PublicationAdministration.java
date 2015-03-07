package ooka.conference.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ooka.conference.entity.Conference;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationPK;
import ooka.conference.entity.PublicationRevision;
import ooka.conference.entity.PublicationRevisionPK;
import ooka.conference.entity.Review;
import ooka.conference.entity.ReviewPK;
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
        PublicationRevision newRevision = new PublicationRevision();
        newRevision.setContent(content);
        PublicationRevisionPK newRevisionPK = new PublicationRevisionPK();
        newRevisionPK.setAuthorId(authorId);
        newRevisionPK.setConferenceId(conferenceId);
        newRevision.setFileName(fileName);
        newRevision.setContentType(contentType);
        newRevision.setPublicationRevisionPK(newRevisionPK);
        em.persist(newRevision);
    }
    
    @Override
    public void reviewPublication(final int reviewerId, final int authorId, final int conferenceId, final byte[] content, final String fileName, final String contentType) throws Exception {
        ReviewPK newReviewPK = new ReviewPK(reviewerId, authorId, conferenceId);
        Review newReview = new Review(newReviewPK);
        newReview.setContent(content);
        newReview.setContentType(contentType);
        newReview.setFileName(fileName);
        newReview.setConference(em.find(Conference.class, conferenceId));
        newReview.setUser(em.find(User.class, reviewerId));
        em.persist(newReview);
    }
}
