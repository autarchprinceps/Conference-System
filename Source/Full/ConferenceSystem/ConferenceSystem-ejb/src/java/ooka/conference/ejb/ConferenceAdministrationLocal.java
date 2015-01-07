/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.ParticipantRole;

/**
 *
 * @author Administrator
 */
@Local
public interface ConferenceAdministrationLocal {

	void rateConference(final int conferenceId, final int userId, final int rating) throws Exception;

	void createConference(final int organizerId, final String conferenceName, final int maxParticipants) throws Exception;

	void registerUserToConference(final int conferenceId, final int userId, final ParticipantRole role) throws Exception;
	
}
