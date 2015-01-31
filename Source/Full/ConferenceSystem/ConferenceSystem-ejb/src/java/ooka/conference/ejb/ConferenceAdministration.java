package ooka.conference.ejb;

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
import ooka.conference.entity.User;

@Stateless
public class ConferenceAdministration implements ConferenceAdministrationLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public void rateConference(int conferenceId, int userId, int rating) throws Exception {

        ConferenceRating newRating = new ConferenceRating();
        newRating.setUser(em.find(User.class, userId));
        newRating.setConference(em.find(Conference.class, conferenceId));
        newRating.setRating(rating);

        ConferenceRatingPK association_pk = new ConferenceRatingPK();
        association_pk.setConferenceId(conferenceId);
        association_pk.setUserId(userId);
        newRating.setConferenceRatingPK(association_pk);

        em.persist(rating);
    }

    @Override
    public void createConference(int organizerId, ConferenceData data) throws Exception {

        User organizer = em.find(User.class, organizerId);

        Conference newConference = new Conference();
        newConference.setName(data.getName());
        newConference.setDate(data.getDate());
        newConference.setParticipantLimit(data.getParticipantLimit());

        em.persist(newConference);
        em.flush();
        em.refresh(newConference);

        ConferenceUserRole newOrganizerRole = new ConferenceUserRole();
        newOrganizerRole.setConference(newConference);
        newOrganizerRole.setUserRole(Role.ORGANIZER.toString());
        newOrganizerRole.setUser(organizer);
        newOrganizerRole.setConference(newConference);

        ConferenceUserRolePK associatioin_pk = new ConferenceUserRolePK();
        associatioin_pk.setConferenceId(newConference.getId());
        associatioin_pk.setUserId(organizerId);

        newOrganizerRole.setConferenceUserRolePK(associatioin_pk);

        em.persist(newOrganizerRole);
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
    }

}
