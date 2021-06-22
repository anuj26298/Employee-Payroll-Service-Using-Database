package com.employeepayroll.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnections {

    public Connection getDBConnection() throws SQLException{
        String dbUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String dbUsername = "root";
        String dbPassword = "root";
        Connection connection;
        connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        return connection;
    }
}
