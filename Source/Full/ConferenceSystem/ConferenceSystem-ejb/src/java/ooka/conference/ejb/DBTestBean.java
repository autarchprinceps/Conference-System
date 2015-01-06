/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import ooka.conference.ejb.DBTestBeanRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import ooka.conference.entity.User;

/**
 *
 * @author bastian
 */
@Stateless
public class DBTestBean implements DBTestBeanRemote{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void test() {
        User tu = new User();
        tu.setName("Olaf");
       em.persist(tu);
    }
    
    
    
}
