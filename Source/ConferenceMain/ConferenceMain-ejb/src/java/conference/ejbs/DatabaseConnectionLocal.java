/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conference.ejbs;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Local;

/**
 *
 * @author autarch
 */
@Local
public interface DatabaseConnectionLocal {
    
    ResultSet execSQL(String sql) throws SQLException;
    
    void execSQLnr(String sql) throws SQLException;
            
}
