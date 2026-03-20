package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconfig {
     private static final String URL = "jdbc:mysql://localhost:3306/garage5";

   private static final String USER = "root";

   private static final String PASS = "lokojit456789";



   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL,USER,PASS);
   }
}
