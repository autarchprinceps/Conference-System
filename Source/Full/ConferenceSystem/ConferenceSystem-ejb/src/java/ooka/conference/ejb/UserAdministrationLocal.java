package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@Local
public interface UserAdministrationLocal {

    void registerUser(UserData data) throws Exception;

    User validateUser(UserData data) throws Exception;

    void changePassword(int userId, String oldPw, String newPw) throws Exception;

}
