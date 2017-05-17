package cenidet.cc.publictransit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    Connection connection;

    private final String USER = "cenidet";
    private String PASSWORD = "cenidet";
    private String IP = "10.0.0.6"; //172.16.10.195, 10.175.121.109
    // 10.175.121.175
    private String DB_NAME = "public_transit";
    private String DRIVER = "com.mysql.jdbc.Driver";

    String URL = "jdbc:mysql://" + IP + "/" + DB_NAME + "?useSSL=false";
    
    private DBConnection(){}

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch ( SQLException exception) {
            System.out.println(exception.toString());
        }catch(ClassNotFoundException exception){
        	System.out.println(exception.toString());
        }
        return connection;
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
    }

}
