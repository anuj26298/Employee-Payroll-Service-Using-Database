package com.employeepayroll.services;

import com.employeepayroll.connections.GetConnections;
import com.employeepayroll.entity.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class EmployeePayrollDBOperations {

    public int id;
    public String name;
    public String salary;
    public String gender;
    public LocalDate startDate;

    public List<Employee> retrieveData(String empName) {
        GetConnections newConnection = new GetConnections();
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = newConnection.getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee_payroll" +
                    " where name=?");
            preparedStatement.setString(1, empName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                salary = resultSet.getString("salary");
                startDate = resultSet.getDate("startdate").toLocalDate();
                gender = resultSet.getString("gender");
                employeeList.add(new Employee(id, name, salary, gender, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public int updateSalary(String name, String salary) {
        GetConnections newConnection = new GetConnections();
        try (Connection connection = newConnection.getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employee_payroll set " +
                    "salary=? where name=?");
            preparedStatement.setDouble(1, Double.parseDouble(salary));
            preparedStatement.setString(2, name);
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int updateSalaryUsingPreparedStatement(String name, String salary) {
        GetConnections newConnection = new GetConnections();

        try (Connection connection = newConnection.getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employee_payroll set " +
                    "salary=? where name=?");
            preparedStatement.setDouble(1, parseDouble(salary));
            preparedStatement.setString(2, name);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Employee> employeesWithInDateRange(LocalDate date1, LocalDate date2) {
        String query = String.format("select * from employee_payroll where startdate between '%s' and '%s'",
                Date.valueOf(date1), Date.valueOf(date2));
        return retrieveData(query);
    }
}
