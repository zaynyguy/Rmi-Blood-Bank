package blood_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnector {
    public Connection con;
    String url = "jdbc:mysql://root@127.0.0.1:3306/bloodbank";
    String user = "root";
    String pass = "lilzayn123@#";

    public Connection mkDataBase() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}

