package ooka.conference.ejb;

import javax.ejb.Local;

@Local
public interface PublicationAdministrationLocal {

    void createPublication(int authorId, int conferenceId, String title);
    
    void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType) throws Exception;

    // void reviewPublication(int authorId, int conferenceId, reviewerId, byte[] content) throws Exception;

}
