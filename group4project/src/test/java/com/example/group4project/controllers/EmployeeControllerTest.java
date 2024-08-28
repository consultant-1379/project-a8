package com.example.group4project.controllers;

import com.example.group4project.controllers.EmployeeController;
import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @MockBean
    EmployeeService service;

    @MockBean
    RepositoryDAO dao;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllEmployees() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employees")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveListEmployees() throws Exception {
        List<Employee> newEmps = new LinkedList<Employee>();
        when(service.saveListEmployees(any())).thenReturn(newEmps);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/employees/createmany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                " {\n" +
                                "        \"name\": \"John Kelly\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "        \"name\": \"Mary Byrne\"\n" +
                                "    }\n" +
                                "]")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

}