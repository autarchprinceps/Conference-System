/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author autarch
 */
@Local
public interface StatefulTestBeanLocal {

    Date getCreationDate();
    
}
