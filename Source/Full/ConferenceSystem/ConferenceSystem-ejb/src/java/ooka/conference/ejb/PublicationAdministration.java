package ooka.conference.ejb;

import javax.ejb.Stateless;

@Stateless
public class PublicationAdministration implements PublicationAdministrationLocal {

	@Override
	public void addNewPublicationRevision(final int authorId, final int conferenceId, final String[] content) throws Exception {
	}

	@Override
	public void review(final int reviewerId, final int authorId, final int conferenceId, final String[] content) throws Exception {
	}

	
}
