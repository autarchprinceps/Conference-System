package ooka.conference.ejb;

import javax.ejb.Local;
import ooka.conference.dto.UserData;

@Local
public interface UserAdministrationLocal {

    public boolean registerUser(UserData data);

    public boolean loginUser(UserData data);

}
