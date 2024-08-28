package com.example.group4project.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employees")
public class Employee {

    @Id
    private String empId;

    private String name;

    public Employee() {

    }

    public Employee(String name ) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    /*
    public String getSignum() {
        return signum;
    }

    public void setSignum(String signum) {
        this.signum = signum;
    }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
