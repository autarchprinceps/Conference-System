/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface PublicationAdministrationLocal {

	void addNewPublicationRevision(final int authorId, final int conferenceId, final String[] content) throws Exception;

	void review(final int reviewerId, final int authorId, final int conferenceId, final String[] content) throws Exception;
	
}
