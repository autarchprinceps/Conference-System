package conference.ejbs;

import conference.businessObjects.*;
import java.util.Date;
import javax.ejb.Stateless;

/*
 * TODO:
 * - Don't use general Exception
 * - DAO
 */

@Stateless
public class ConferenceManagement implements ConferenceManagementLocal {

    @Override
    public int createConference(final int organizerId, final String name, final int maxParticipants, final Date start, final Date end) throws Exception {
        User user = User.getUserById(organizerId);
        if(user instanceof Organizer) {
            Organizer organizer = (Organizer)user;
            if(maxParticipants > 200 && organizer.getRating() < -3) {
                throw new Exception("createConference: Selected organizer has insufficient rating to create new conference");
            } else {
                Conference conference = new Conference(name, start, end, organizer);
                organizer.organizeConference(conference);
                return conference.getId();
            }
        } else {
            throw new Exception("createConference: Selected user is no organizer");
        }
    }

    @Override
    public void registerToConference(final int conferenceId, final int userId) throws Exception {
        if(User.getUserById(userId) != null) {
            if(Conference.getConferenceById(conferenceId) != null) {
                Conference.getConferenceById(conferenceId).register(userId);
            } else {
                throw new Exception("registerToConference: Selected conferenceId doesn't belong to any existing conference");
            }
        } else {
            throw new Exception("registerToConference: Selected userId doesn't belong to any existing user");
        }
    }

    @Override
    public void rateConference(final int conferenceId, final int userId, final int rating) throws Exception {
        if(rating > 5 || rating < -5) {
            throw new Exception("rateConference: Rating value out of bounds");
        } else {
            Conference.getConferenceById(conferenceId).rate(userId, rating);
        }
    }

    @Override
    public int getConferenceRating(final int conferenceId) throws Exception {
        if(Conference.getConferenceById(conferenceId) != null) {
            return (int)Math.round(Conference.getConferenceById(conferenceId).getRating());
        } else {
            throw new Exception("getConferenceRating: Selected conferenceId doesn't match any exiting conference");
        }
    }

    @Override
    public int getOrganizerRating(final int organizerId) throws Exception {
        User user = User.getUserById(organizerId);
        if(user instanceof Organizer) {
            Organizer organizer = (Organizer)user;
            return (int)Math.round(organizer.getRating());
        } else {
            throw new Exception("getOrganizerRating: Selected user is not an organozer");
        }
    }

    
}
