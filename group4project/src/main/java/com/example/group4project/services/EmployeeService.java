package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.exception.CustomException;
import com.example.group4project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    RepositoryDAO dao;

    public EmployeeService() {

    }

    public Employee saveEmployee(Employee e) {
        return repository.save(e);
    }

    public List<Employee> saveListEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    public Optional<Employee> getTeamById(String id) {
        return repository.findById(id);
    }

    public Employee updateEmployee(String id, Employee emp) throws CustomException {
        Optional<Employee> empOpt = repository.findById(id);

        if(!empOpt.isPresent()) {
            throw new CustomException("404", "Can not find an employee to update with id: " + id);
        }

        Employee empToModify = empOpt.get();
        empToModify.setName(emp.getName());

        repository.save(empToModify);
        return empToModify;
    }

    public void deleteEmployeeById(String id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
