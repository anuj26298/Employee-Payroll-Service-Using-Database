package com.employeepayroll.services;

import org.junit.Assert;
import org.junit.Test;


public class AggregationFunctionsTest {

    @Test
    public void givenEmployeeSalariesInDB_ReturnSumOfAllSalaries() {
        AggregationFunctionsInDB aggregationFunction = new AggregationFunctionsInDB();
        Assert.assertEquals("3705000.0", aggregationFunction.salarySum());
    }
}
