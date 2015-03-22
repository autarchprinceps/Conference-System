package ooka.conference.ejb;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;
import ooka.conference.entity.Conference;
import ooka.conference.entity.ConferenceRating;
import ooka.conference.entity.ConferenceRatingPK;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.ConferenceUserRolePK;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationPK;
import ooka.conference.entity.User;

@PermitAll
@Stateless
public class ConferenceAdministration implements ConferenceAdministrationLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    SearchLocal searchEJB;

    @Override
    public void rateConference(int confId, int userId, int rating) throws Exception {

        if (rating > 2 || rating < -2) {
            throw new Exception("Rating out of bounds");
        }

        ConferenceRatingPK ratingPK = new ConferenceRatingPK(userId, confId);
        ConferenceRating ratingEntity = em.find(ConferenceRating.class, ratingPK);

        if (ratingEntity != null) {
            ratingEntity.setRating(rating);
            em.merge(ratingEntity);
        } else {
            ConferenceRating newRating = new ConferenceRating();
            newRating.setRating(rating);
            newRating.setConferenceRatingPK(ratingPK);
            em.persist(newRating);
        }
    }

    @Override
    public void createConference(int userId, ConferenceData data) throws Exception {

        if (data.getComittee().isEmpty()) {
            throw new Exception("Can not create conference without reviewers");
        }

        if (data.getParticipantLimit() > 200 && searchEJB.getAverageRatingOfOrganizer(userId) < 0) {
            throw new Exception("The average rating of your previous conferences is too low to organize conferences with more than 200 participants.");
        }

        Conference newConference = new Conference(userId);
        newConference.setName(data.getName());
        newConference.setDate(data.getDate());
        newConference.setParticipantLimit(data.getParticipantLimit());

        em.persist(newConference);
        em.flush();
        em.refresh(newConference);

        // register organizer
        registerToConference(newConference.getId(), userId, Role.ORGANIZER);

        // register reviewer
        for (User reviewer : data.getComittee()) {
            registerToConference(newConference.getId(), reviewer.getId(), Role.REVIEWER);
        }
    }

    @Override
    public void registerToConference(int confId, int userId, Role role) throws Exception {
        Conference conf = em.find(Conference.class, confId);

        if (conf.getConferenceUserRoleCollection().size() >= conf.getParticipantLimit()) {
            throw new Exception("Could not register, because the participant limit for this conference has been reached.");
        }

        ConferenceUserRolePK rolePK = new ConferenceUserRolePK(userId, confId);
        ConferenceUserRole newConferenceUserRole = new ConferenceUserRole(rolePK);
        newConferenceUserRole.setUserRole(role.toString());
        em.persist(newConferenceUserRole);
    }

    @Override
    public void deregisterToConference(int confId, int userId) throws Exception {
        ConferenceUserRolePK rolePK = new ConferenceUserRolePK(userId, confId);
        em.remove(em.find(ConferenceUserRole.class, rolePK));

        ConferenceRatingPK ratingPK = new ConferenceRatingPK(userId, confId);
        ConferenceRating rating = em.find(ConferenceRating.class, ratingPK);
        if (rating != null) {
            em.remove(rating);
        }

        PublicationPK pubPK = new PublicationPK(confId, userId);
        Publication pub = em.find(Publication.class, pubPK);
        if (pub != null) {
            em.remove(pub);
        }
    }

    @Override
    public void cancelConference(int conferenceId) throws Exception {
        em.remove(em.find(Conference.class, conferenceId));
    }

}
