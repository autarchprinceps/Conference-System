package conference.ejbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class DatabaseConnection implements DatabaseConnectionLocal {
    private Connection conn;
    
    @PostConstruct
    private void init() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String url = "jdbc:postgresql://dumbo.inf.fh-bonn-rhein-sieg.de/probin2s";
            Properties props = new Properties();
            props.setProperty("user", "probin2s");
            props.setProperty("password", "probin2s");

            conn = DriverManager.getConnection(url, props);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ResultSet execSQL(String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }
    
    @Override
    public void execSQLnr(String sql) throws SQLException {
        conn.createStatement().executeUpdate(sql);
    }
}
