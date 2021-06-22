package com.employeepayroll.connections;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class getConnectionsTest {

    @Test
    public void givenDataBase_WhenCheckingJDBC_Return() throws SQLException {
        GetConnections getconnections = new GetConnections();
        Connection connection = getconnections.getDBConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}
