package com.employeepayroll.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    public int id;
    public String name;
    public String salary;
    public String gender;
    public LocalDate startDate;

    public Employee(int id, String name, String salary,String gender, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", gender=" + gender +
                ", startDate=" + startDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Employee employee = (Employee)obj;

        return (id == employee.id &&
                (employee.salary).equals(salary) &&
                employee.gender == gender &&
                (name == employee.name) &&
                startDate == employee.startDate)
                ;

    }
}
