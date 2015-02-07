package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;

@Local
public interface ConferenceAdministrationLocal {

    public void rateConference(int conferenceId, int userId, int rating) throws Exception;

    public void createConference(int organizerId, ConferenceData data) throws Exception;

    public void cancelConference(int conferenceId) throws Exception;
    
    public void registerToConference(int conferenceId, int userId, Role role) throws Exception;

    public void deregisterToConference(int conferenceId, int userId) throws Exception;

}
