package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    @Mock
    RepositoryDAO repositoryDAO;

    @InjectMocks
    ItemService itemService;

    @Test
    public void getAllItems(){
        List<Item> testItems = new LinkedList<Item>();
        testItems.add(new Item("retroId","db","title","desc"));
        List<Item> test = itemService.getAllItems();
        Assert.assertEquals("db",testItems.get(0).getCategory());
    }

    @Test
    public void addItemToRetrospective(){
//        Item testItem = new Item("re","db","title","desc");
        when(repositoryDAO.addItemToRetrospective(any())).thenReturn(new Item("re","db","title","desc"));
        Item test = itemService.addItemToRetrospective(new Item("t","e","s","t"));
        Assert.assertEquals("re",test.getRetroId());
    }

    @Test
    public void deleteAllItems(){
        when(repositoryDAO.deleteAllItems()).thenReturn(new LinkedList<Item>());
        List<Item> test = itemService.deleteAllItems();
        Assert.assertEquals(0,test.size());
    }


}
