package com.employeepayroll.services;

import com.employeepayroll.connections.GetConnections;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class AggregationFunctionsTest {
    GetConnections newConnection = new GetConnections();

    @Test
    public void givenEmployeeSalariesInDB_ReturnSumOfAllSalaries() {
        AggregationFunctionsInDB aggregationFunction = new AggregationFunctionsInDB();
        Assert.assertEquals("3705000.0", aggregationFunction.salarySum());
    }
}
