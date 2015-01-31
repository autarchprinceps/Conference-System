package ooka.conference.ejb;

import javax.ejb.Local;

@Local
public interface PublicationAdministrationLocal {

    void createPublication(String title, int authorId, int conferenceId);
    
    void revisePublication(int authorId, int conferenceId, String content) throws Exception;

    void reviewPublication(int reviewerId, int authorId, int conferenceId, String content) throws Exception;

}
