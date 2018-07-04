package org.goelas.dto;

import java.util.Objects;

/**
 * Created by ash_g on 21/05/2017.
 */
public class Employee {


    String name;
    String gpn;
    String rank;
    String division;
    String department;


    public Employee(String name, String gpn, String rank, String division, String department) {
        this.name = name;
        this.gpn = gpn;
        this.rank = rank;
        this.division = division;
        this.department = department;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gpn='" + gpn + '\'' +
                ", rank='" + rank + '\'' +
                ", division='" + division + '\'' +
                ", department='" + department + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(gpn, employee.gpn) &&
                Objects.equals(rank, employee.rank) &&
                Objects.equals(division, employee.division) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, gpn, rank, division, department);
    }

    public String getName() {
        return name;
    }

    public String getGpn() {
        return gpn;
    }

    public String getRank() {
        return rank;
    }
}
