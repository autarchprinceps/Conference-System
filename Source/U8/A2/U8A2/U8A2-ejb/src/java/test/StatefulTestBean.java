/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

/**
 *
 * @author autarch
 */
@Stateful
public class StatefulTestBean implements StatefulTestBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private Date creation;
    
    @PostConstruct
    private void init() {
        creation = new Date();
    }

    @Override
    public Date getCreationDate() {
        return creation;
    }
    
    
}
