package ooka.conference.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@Stateless // TODO Why Stateful? Replaced with Stateless
public class UserAdministration implements UserAdministrationLocal {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    SearchLocal searchEJB;

    @Override
    public void registerUser(UserData data) throws Exception {
        User newUser = new User();
        newUser.setName(data.getName());
        newUser.setPassword(data.getPassword());
        em.persist(newUser);
    }

    @Override
    public User validateUser(UserData data) throws Exception {
        Query checkQuery = em.createNamedQuery("User.check");
        checkQuery.setParameter("name", data.getName());
        checkQuery.setParameter("password", data.getPassword());

        List results = checkQuery.getResultList();

        if (results.size() == 1) {
            return (User) results.get(0);
        }
        return null;
    }

    @Override
    public void changePassword(final int userId, final String oldPw, final String newPw) throws Exception {
        User user = searchEJB.searchUserById(userId);
        if(user.getPassword().equals(oldPw)) {
            user.setPassword(newPw);
            em.persist(newPw);
        } else {
            throw new Exception("Old password incorrect");
        }
    }

}
