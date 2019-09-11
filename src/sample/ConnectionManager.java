package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class ConnectionManager
{
    private static Connection conn;

   static Connection getConnection()
   {
       try
       {
           String driverName = "com.mysql.jdbc.Driver";
           Class.forName(driverName);
           try
           {
               String url = "jdbc:mysql://127.0.0.1:3306/travelexperts";
               String username = "root";
               String password = "";
               conn = DriverManager.getConnection(url, username, password);
           }
           catch (SQLException ex)
           {
               System.out.println("Failed to connect");
           }
       }
       catch (ClassNotFoundException ex)
       {
           System.out.println("Driver not found");
       }
       return conn;
   }

}
