package ooka.conference.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import ooka.conference.dto.UserData;
import ooka.conference.entity.User;

@Stateful
public class UserAdministration implements UserAdministrationLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean registerUser(UserData data) {
        User newUser = new User();
        newUser.setName(data.getName());
        newUser.setPassword(data.getPassword());
        try {
            em.persist(newUser);
        } catch (EntityExistsException e) {
            return false;
        } catch (ConstraintViolationException ce) {
            System.out.println(ce.getConstraintViolations());
            return false;
        }

        return true;
    }

    @Override
    public boolean loginUser(UserData data) {
        Query checkQuery = em.createNamedQuery("User.check");
        checkQuery.setParameter("name", data.getName());
        checkQuery.setParameter("password", data.getPassword());
        try {
            checkQuery.getSingleResult();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
