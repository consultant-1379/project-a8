package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    RepositoryDAO repositoryDAO;

    public List<Item> getAllItems() { return repositoryDAO.getAllItems(); }

    public Item getItemById(String id) { return repositoryDAO.getByItemId(id); }

    public Item addItemToRetrospective(Item item) {
        return repositoryDAO.addItemToRetrospective(item);
    }

    public Item createItem(Item item) {
        return repositoryDAO.createItem(item);
    }

    public List<Item> deleteAllItems() {
         return repositoryDAO.deleteAllItems();
    }
}
