package ooka.conference.ejb;

import java.util.Date;
import javax.ejb.Local;

@Local
public interface PublicationAdministrationLocal {

    void createPublication(int authorId, int conferenceId, String title);
    
    void deletePublication(int authorId, int conferenceId);

    void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception;

    void reviewPublication(int reviewerId, int authorId, int conferenceId, byte[] content, String fileName, String contentType, Date date) throws Exception;

    void addReviewerToPublication(int reviewerId, int authorId, int conferenceId) throws Exception;
}
