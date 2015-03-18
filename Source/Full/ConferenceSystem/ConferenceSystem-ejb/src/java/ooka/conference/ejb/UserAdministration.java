package ooka.conference.ejb;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@PermitAll
@Stateless
public class UserAdministration implements UserAdministrationLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void registerUser(UserData data) throws Exception {
        User newUser = new User();
        newUser.setName(data.getName());
        newUser.setPassword(data.getPassword());
        em.persist(newUser);
    }

    @Override
    public User validateUser(UserData data) throws Exception {
        Query validateQuery = em.createNamedQuery("User.check");
        validateQuery.setParameter("name", data.getName());
        validateQuery.setParameter("password", data.getPassword());
        return (User) validateQuery.getSingleResult();
    }

    @Override
    public void changePassword(int userId, String oldPw, String newPw) throws Exception {
        User user = em.find(User.class, userId);
        if (user.getPassword().equals(oldPw)) {
            user.setPassword(newPw);
            em.merge(user);
        } else {
            throw new Exception("Old password incorrect");
        }
    }

}
