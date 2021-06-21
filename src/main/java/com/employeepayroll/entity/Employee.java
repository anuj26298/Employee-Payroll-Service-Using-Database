package com.employeepayroll.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    public int id;
    public String name;
    public String salary;
    public LocalDate startDate;

    public Employee(int id, String name, String salary, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
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
                (name == employee.name) &&
                startDate == employee.startDate)
                ;

    }
}
