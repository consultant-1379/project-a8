package com.example.group4project.controllers;

import com.example.group4project.entities.Employee;
import com.example.group4project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        // get all documents from MongoDB database
        List<Employee> employees = service.getAllEmployees();

        if(employees == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(employees);
        }
    }

    @PostMapping("/createmany")
    public ResponseEntity<List<Employee>> saveListEmployees(@RequestBody List<Employee> employees) {
        // save a list of customers to MongoDB database
        List<Employee> newEmployees = service.saveListEmployees(employees);

        if(newEmployees == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newEmployees);
        }
    }
}
