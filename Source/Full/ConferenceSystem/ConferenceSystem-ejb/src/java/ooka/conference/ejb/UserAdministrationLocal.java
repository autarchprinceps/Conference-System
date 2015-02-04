package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@Local
public interface UserAdministrationLocal {

    public void registerUser(UserData data);

    public User validateUser(UserData data);

}
