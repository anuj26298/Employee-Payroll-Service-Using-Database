package com.employeepayroll.services;

import com.employeepayroll.connections.getConnections;
import com.employeepayroll.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBOperations {

    Connection connection;
    public int id;
    public String name;
    public  String salary;
    public LocalDate startDate;

    public List<Employee> retrieveData(String query){
        getConnections newConnection = new getConnections();
        List<Employee> employeeList = new ArrayList<>();
        try(Connection connection = newConnection.getDBConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                salary = resultSet.getString("salary");
                startDate = resultSet.getDate("startdate").toLocalDate();
                employeeList.add(new Employee(id,name,salary,startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public int updateSalary(String name, String salary){
        getConnections newConnection = new getConnections();
        String query = String.format("update employee_payroll set salary='%.2f' where name='%s'", Double.parseDouble(salary), name);

        try(Connection connection = newConnection.getDBConnection() ){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
