package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.Role;

@Local
public interface ConferenceAdministrationLocal {

    void rateConference(int confId, int userId, int rating) throws Exception;

    void createConference(int userId, ConferenceData data) throws Exception;

    void cancelConference(int confId) throws Exception;

    void registerToConference(int confId, int userId, Role role) throws Exception;

    void deregisterToConference(int confId, int userId) throws Exception;

}
