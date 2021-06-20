package com.employeepayroll.connections;

import org.junit.Assert;
import org.junit.Test;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.SQLException;

public class getConnectionsTest {

    @Test
    public void givenDataBase_WhenCheckingJDBC_Return() throws SQLException {
        getConnections getconnections = new getConnections();
        Connection connection = getconnections.getDBConnection();
        Assert.assertTrue(connection != null);

    }
}
