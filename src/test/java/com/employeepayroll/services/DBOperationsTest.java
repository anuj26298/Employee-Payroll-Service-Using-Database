package com.employeepayroll.services;

import com.employeepayroll.entity.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DBOperationsTest {

    @Test
    public void givenQueryToReadData_WhenExecuted_ReturnTrueIfSuccessfullyExecuted() {
        EmployeePayrollDBOperations dbOperaitons = new EmployeePayrollDBOperations();
        String query = "select * from employee_payroll";
        List<Employee> employeeList = dbOperaitons.retrieveData(query);
        Assert.assertEquals(5, employeeList.size());

    }
    @Test
    public void givenUpdateQuery_WhenExecuted_ReturnNumberOfRowsAffected(){
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        String name="Anuj", salary="3000000";
        Assert.assertEquals(1,dbOperations.updateSalary(name,salary));
    }
}
