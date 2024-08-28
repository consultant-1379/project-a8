package com.example.group4project.controllers;

import com.example.group4project.entities.Item;
import com.example.group4project.entities.Retrospective;
import com.example.group4project.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/items")
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("")
    public ResponseEntity<List<Item>> getAllItems() {

        // get all documents from MongoDB database
        List<Item> allItems = itemService.getAllItems();

        if(allItems == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(allItems);
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable String itemId ) {
        Item item = itemService.getItemById(itemId);

        if(item==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(item);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Item> addItemToRetrospective(@RequestBody Item item) {

        Item newItem = itemService.addItemToRetrospective(item);

        if(newItem == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newItem);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {

        Item newItem = itemService.createItem(item);

        if(newItem == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newItem);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<List<Item>> deleteAllItems() {
        List<Item> items = itemService.deleteAllItems();

        if(items==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(items);
        }
    }
}
