package com.employeepayroll.services;

import com.employeepayroll.connections.getConnections;
import com.employeepayroll.entity.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RetrieveDataTest {

    @Test
    public void givenQueryToReadData_WhenExecuted_ReturnTrueIfSuccessfullyExecuted() throws SQLException {
        RetrieveData retrieveData = new RetrieveData();
        String query = "select * from employee_payroll";
        List<Employee> employeeList = retrieveData.ReadTableData(query);
        Assert.assertEquals(4, employeeList.size());
    }
}
