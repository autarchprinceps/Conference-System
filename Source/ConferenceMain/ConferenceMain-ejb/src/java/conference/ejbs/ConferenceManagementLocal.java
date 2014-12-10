package conference.ejbs;

import java.util.Date;
import javax.ejb.Local;

@Local
public interface ConferenceManagementLocal {

    int createConference(final int organizerId, final String name, final int maxParticipants, final Date start, final Date end) throws Exception;

    void registerToConference(final int conferenceId, final int userId) throws Exception;

    void rateConference(final int conferenceId, final int userId, final int rating) throws Exception;

    int getConferenceRating(final int conferenceId) throws Exception;

    int getOrganizerRating(final int organizerId) throws Exception;
    
}
