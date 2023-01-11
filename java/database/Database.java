package database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static final String DB = "gras_test";
    public static final String REMOTE_IP = "127.0.0.1:3306/";
    public static Connection getConnection() {
        try {
            new Driver();
            return DriverManager.getConnection("jdbc:mysql://"+REMOTE_IP+DB+"?user=root&password=t110598025&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return null;
        }
    }
}

