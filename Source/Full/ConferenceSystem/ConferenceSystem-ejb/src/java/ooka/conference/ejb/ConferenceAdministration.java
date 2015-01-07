package ooka.conference.ejb;

import javax.ejb.Stateless;
import ooka.conference.dto.ParticipantRole;

@Stateless
public class ConferenceAdministration implements ConferenceAdministrationLocal {

	@Override
	public void rateConference(final int conferenceId, final int userId, final int rating) throws Exception {
	}

	@Override
	public void createConference(final int organizerId, final String conferenceName, final int maxParticipants) throws Exception {
	}

	@Override
	public void registerUserToConference(final int conferenceId, final int userId, final ParticipantRole role) throws Exception {
	}
	
	// TODO register comitee
}
