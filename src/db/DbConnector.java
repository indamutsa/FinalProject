package db;

import java.sql.*;

public class DbConnector
{
    /*This function helps me connect with the database*/
    public Connection connect()
    {
        /*Mysql connection*/
        Connection conn = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            String db = "jdbc:mysql://localhost:3306/HwFinal";
            conn = DriverManager.getConnection(db, "root", "Jehovah@1");

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
