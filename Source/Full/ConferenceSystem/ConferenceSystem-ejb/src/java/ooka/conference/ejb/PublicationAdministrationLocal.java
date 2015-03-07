package ooka.conference.ejb;

import java.util.List;
import javax.ejb.Local;
import ooka.conference.entity.Review;

@Local
public interface PublicationAdministrationLocal {

    void createPublication(int authorId, int conferenceId, String title);
    
    void revisePublication(int authorId, int conferenceId, byte[] content, String fileName, String contentType) throws Exception;

    void reviewPublication(final int reviewerId, final int authorId, final int conferenceId, final byte[] content, final String fileName, final String contentType) throws Exception;

}
