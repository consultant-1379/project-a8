package com.example.group4project.controllers;

import com.example.group4project.entities.Item;
import com.example.group4project.services.ItemService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ItemService itemService;


    @Test
    public void getAllItems() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/items")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
//        System.out.println("OUTPUT1" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void addItemToRetrospective() throws Exception{
        Item newItem = new Item("123","db","s1","test");
        when(itemService.addItemToRetrospective(any())).thenReturn(newItem);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/items/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\" : \"testId\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void deleteAllItems() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/items/deleteAll")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }
}
