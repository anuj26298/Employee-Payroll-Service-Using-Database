package com.employeepayroll.services;

import com.employeepayroll.entity.Employee;
import org.junit.Assert;
import org.junit.Test;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DBOperationsTest {

    @Test
    public void givenQueryToReadData_WhenExecuted_ReturnTrueIfSuccessfullyExecuted() {
        EmployeePayrollDBOperations dbOperaitons = new EmployeePayrollDBOperations();
        List<Employee> employeeList = dbOperaitons.retrieveData("Harsh");
        Assert.assertEquals("30000", employeeList.get(0).salary);

    }
    @Test
    public void givenUpdateQuery_WhenExecuted_ReturnNumberOfRowsAffected(){
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        String name="Anuj", salary="3000000";
        Assert.assertEquals(1,dbOperations.updateSalary(name,salary));
    }

    @Test
    public void givenPreparedStatementQuery_WhenExecuted_ReturnNumberOfRowsAffected() {
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        Assert.assertEquals(1,dbOperations.updateSalaryUsingPreparedStatement("Apoorv", "300000"));

    }

    @Test
    public void givenDateRange_ReturnEmployeesBetweenGivenDateRange() {
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        Assert.assertEquals(3, dbOperations.employeesWithInDateRange(LocalDate.of(2020,12,23)
                ,LocalDate.of(2021,04,26)).size());
    }

    @Test
    public void givenEmployeeData_WhenInsertedInDatabase_ReturnNumberOfRowsAffected() {
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        List<Employee> employeeList = Collections.singletonList(new Employee(8, "Shiveta", "400000",
                "F", LocalDate.parse("2020-06-20")));
        Assert.assertEquals(1,dbOperations.addEmployee(employeeList));
    }

    @Test
    public void givenEmployeeIdAndSalary_WhenUpdatesPayrollDetails_ReturnTrue() {
        EmployeePayrollDBOperations dbOperations = new EmployeePayrollDBOperations();
        Assert.assertEquals(1,dbOperations.addPayrollDetails(1,"20000"));
    }
}
