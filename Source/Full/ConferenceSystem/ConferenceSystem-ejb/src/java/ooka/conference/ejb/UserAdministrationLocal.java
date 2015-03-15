package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@Local
public interface UserAdministrationLocal {

    public void registerUser(UserData data) throws Exception;

    public User validateUser(UserData data) throws Exception;

    void changePassword(final int userId, final String oldPw, final String newPw) throws Exception;

}
