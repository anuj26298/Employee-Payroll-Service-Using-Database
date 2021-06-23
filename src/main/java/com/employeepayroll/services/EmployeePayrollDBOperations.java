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

    public int addEmployee(List<Employee> employeeList) {
        GetConnections newConnection = new GetConnections();
        try {
            PreparedStatement preparedStatement = newConnection.getDBConnection()
                    .prepareStatement("insert into employee_payroll values(" +
                            "?,?,?,?,?)");
            preparedStatement.setInt(1, employeeList.get(0).id);
            preparedStatement.setString(2, employeeList.get(0).name);
            preparedStatement.setString(3, employeeList.get(0).salary);
            preparedStatement.setString(4, employeeList.get(0).gender);
            preparedStatement.setDate(5, Date.valueOf(employeeList.get(0).startDate));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public List<Employee> addPayrollDetails(List<Employee> employeeList) {
        GetConnections newConnection = new GetConnections();
        try {
            newConnection.getDBConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addEmployee(employeeList);

        try {
            newConnection.getDBConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        double basePay = Double.parseDouble(employeeList.get(0).salary);
        double deduction = basePay * 0.2;
        double taxablePay = basePay - deduction;
        double tax = taxablePay * 0.1;
        double netPay = basePay - tax;
        try {
            PreparedStatement preparedStatement = newConnection.getDBConnection()
                    .prepareStatement("insert into payroll_details values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, employeeList.get(0).id);
            preparedStatement.setDouble(2, basePay);
            preparedStatement.setDouble(3, deduction);
            preparedStatement.setDouble(4, taxablePay);
            preparedStatement.setDouble(5, tax);
            preparedStatement.setDouble(6, netPay);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery("select * from employee_payroll");

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

        try {
            newConnection.getDBConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            newConnection.getDBConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            newConnection.getDBConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeeList;
    }
}
