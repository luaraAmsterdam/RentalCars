package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB
{

    private static final String url;
    private static final String user;
    private static final String password;

    private static Connection connection = null;

    static
    {
        try
        {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        } catch (ClassNotFoundException ex)
        {
            throw new RuntimeException("There are not mysql jdbc driver!");
        }
        url = "jdbc:firebirdsql://localhost/E:/Study/ArchPO/DB/RENTCAR.fdb"; //?lc_ctype=WIN1251
        user = "sysdba";
        password = "masterkey";
        if (url == null || user == null || password == null)
        {
            throw new RuntimeException("Bad configuration file for database!");
        }
    }

    public static Connection getConnection() {
        if(connection == null)
            try {
                System.out.println("Creating new connection");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                throw new RuntimeException("Can not connect to the database");
            }
        return connection;
    }
    
//    public static void closeConnection() {
//        if (connection != null) 
//            try {
//                connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}