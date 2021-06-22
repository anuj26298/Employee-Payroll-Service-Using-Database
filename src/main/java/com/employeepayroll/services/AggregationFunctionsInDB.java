package com.employeepayroll.services;

import com.employeepayroll.connections.GetConnections;
import com.employeepayroll.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AggregationFunctionsInDB {
    GetConnections newConnection = new GetConnections();

    public String runAggregationQuery(String query){
        try(Connection connection = newConnection.getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String salary=null;
            while (resultSet.next()){
                salary = resultSet.getString("salary");
            }
            return salary;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String salarySum(){
        return runAggregationQuery("select sum(salary) as " +
                "salary from employee_payroll");
    }

    public void salaryAverage(){
        runAggregationQuery("select gender,avg(salary) as salary" +
                "from employee_payroll group by gender");
    }

    public void minimumSalary(){
        runAggregationQuery("select gender,min(salary) as salary " +
                "from employee_payroll group by gender");
    }

    public void maximumSalary(){
        runAggregationQuery("select gender,max(salary) as salary " +
                "from employee_payroll group by gender");
    }
}
