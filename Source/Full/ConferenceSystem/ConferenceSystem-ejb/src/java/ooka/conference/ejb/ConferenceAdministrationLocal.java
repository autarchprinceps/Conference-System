package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;

@Local
public interface ConferenceAdministrationLocal {

    void rateConference(int conferenceId, int userId, int rating) throws Exception;

    void createConference(int organizerId, ConferenceData data) throws Exception;

    void registerToConference(int conferenceId, int userId, Role role) throws Exception;

}
