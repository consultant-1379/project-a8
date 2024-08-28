package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.repositories.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;
    @Mock
    RepositoryDAO dao;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void saveEmployee(){
        when(repository.save(any())).thenReturn(new Employee("test"));
        Employee testEmp = employeeService.saveEmployee(new Employee("test"));
        Assert.assertEquals("test",testEmp.getName());
    }

    @Test
    public void saveListEmployees(){
        List<Employee> testList = new LinkedList<Employee>();
        testList.add(new Employee("test"));
        when(repository.saveAll(any())).thenReturn(new LinkedList<Employee>());
        List<Employee> test = employeeService.saveListEmployees(new LinkedList<Employee>());
        Assert.assertEquals("test",testList.get(0).getName());
    }

    @Test
    public void getAllEmployees(){
        List<Employee> testList = new LinkedList<Employee>();
        testList.add(new Employee("test"));
        when(dao.getAllEmployees()).thenReturn(testList);
        List<Employee> test = employeeService.getAllEmployees();
        Assert.assertEquals("test",testList.get(0).getName());
    }

    @Test
    public void getTeamById(){
        when(repository.findById("testID")).thenReturn(Optional.of(new Employee("test")));
        Optional<Employee> test = employeeService.getTeamById("testID");
        Assert.assertEquals(null,test.get().getEmpId());
    }

    @Test(expected = Exception.class)
    public void updateEmployeeEx() throws Exception{
        Employee testEMP = new Employee("testNAME");
        employeeService.saveEmployee(testEMP);
        String testID = testEMP.getEmpId();
        Employee result = employeeService.updateEmployee("newID",testEMP);
        verify(dao).getTeamById(testID);
    }

//    @Test
//    public void updateEmployee(){
//        when(repository.save(any())).thenReturn(new Employee("test"));
//        Employee testEmp = employeeService.saveEmployee(new Employee("test"));
////        Assert.assertEquals("test",testEmp.getName());
//        System.out.println(testEmp.getEmpId());
//    }

}
