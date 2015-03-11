package ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceRating;
import ooka.conference.entity.ConferenceRatingPK;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.ConferenceUserRolePK;
import ooka.conference.entity.User;

@Stateless
public class ConferenceAdministration implements ConferenceAdministrationLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void rateConference(int conferenceId, int userId, int rating) throws Exception {

        /*
         if (rating > 2 || rating < -2) {
         throw new Exception("Rating out of bounds");
         }
         */
        ConferenceRating newRating = new ConferenceRating();
        newRating.setUser(em.find(User.class, userId));
        newRating.setConference(em.find(Conference.class, conferenceId));
        newRating.setRating(rating);

        ConferenceRatingPK association_pk = new ConferenceRatingPK();
        association_pk.setConferenceId(conferenceId);
        association_pk.setUserId(userId);
        newRating.setConferenceRatingPK(association_pk);

        em.persist(newRating);
    }

    @Override
    public void createConference(int organizerId, ConferenceData data) throws Exception {

        Conference newConference = new Conference();
        newConference.setName(data.getName());
        newConference.setDate(data.getDate());
        newConference.setParticipantLimit(data.getParticipantLimit());

        em.persist(newConference);
        em.flush();
        em.refresh(newConference);

        registerToConference(newConference.getId(), organizerId, Role.ORGANIZER);

        for (User reviewer : data.getComittee()) {
            registerToConference(newConference.getId(), reviewer.getId(), Role.REVIEWER);
        }
    }

    @Override
    public void registerToConference(int conferenceId, int userId, Role role) throws Exception {
        ConferenceUserRole newConferenceUserRole = new ConferenceUserRole();
        newConferenceUserRole.setConference(em.find(Conference.class, conferenceId));
        newConferenceUserRole.setUser(em.find(User.class, userId));
        newConferenceUserRole.setUserRole(role.toString());

        ConferenceUserRolePK associatioin_pk = new ConferenceUserRolePK();
        associatioin_pk.setConferenceId(conferenceId);
        associatioin_pk.setUserId(userId);

        newConferenceUserRole.setConferenceUserRolePK(associatioin_pk);

        em.persist(newConferenceUserRole);
        em.flush();
    }

    @Override
    public void deregisterToConference(int conferenceId, int userId) throws Exception {
        Query roleQuery = em.createNamedQuery("ConferenceUserRole.deleteByConferenceIdAndUserId");
        roleQuery.setParameter("conferenceId", conferenceId);
        roleQuery.setParameter("userId", userId);
        roleQuery.executeUpdate();

        Query reviewQuery = em.createNamedQuery("Review.deleteByConferenceIdAndReviewerId");
        reviewQuery.setParameter("reviewerId", userId);
        reviewQuery.setParameter("conferenceId", conferenceId);
        reviewQuery.executeUpdate();

        Query revisionQuery = em.createNamedQuery("PublicationRevision.deleteByConferenceIdAndAuthorId");
        revisionQuery.setParameter("conferenceId", conferenceId);
        revisionQuery.setParameter("authorId", userId);
        revisionQuery.executeUpdate();

        Query pubQuery = em.createNamedQuery("Publication.deleteByConferenceIdAndAuthorId");
        pubQuery.setParameter("conferenceId", conferenceId);
        pubQuery.setParameter("userId", userId);
        pubQuery.executeUpdate();

        Query ratingQuery = em.createNamedQuery("ConferenceRating.deleteByConferenceIdAndUserId");
        ratingQuery.setParameter("userId", userId);
        ratingQuery.setParameter("conferenceId", conferenceId);
        ratingQuery.executeUpdate();
    }

    @Override
    public void cancelConference(int conferenceId) throws Exception {
        em.remove(em.find(Conference.class, conferenceId));
    }

}
