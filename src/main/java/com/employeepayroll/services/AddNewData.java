package com.employeepayroll.services;

import com.employeepayroll.connections.getConnections;
import com.employeepayroll.entity.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddNewData {
    
    public void addData(String query) throws SQLException {

        getConnections newConnection = new getConnections();
        Connection connection = newConnection.getDBConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
