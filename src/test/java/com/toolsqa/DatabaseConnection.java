package com.toolsqa;

import org.testng.annotations.Test;

import java.sql.*;

public class DatabaseConnection {

    @Test
    public void jdbcconnection() throws SQLException, ClassNotFoundException {
    String URL = "jdbc:mysql://localhost:3036/emp";
    String Username = "root";
    String Password = "guru99";
    String query = "Select * from employee";

    Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next())
        {
            String Name = rs.getString(1);
            String upassword = rs.getString(2);
            System.out.println(Name +" has password " +upassword);


        }
        con.close();



    }
}
