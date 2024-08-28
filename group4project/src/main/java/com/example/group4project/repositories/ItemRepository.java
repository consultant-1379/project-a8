package com.example.group4project.repositories;

import com.example.group4project.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByRetroId(String retroId);

    List<Item> findByCategory(String category);

    Optional<Item> findByItemId(String itemId);

    Item deleteByItemId(String itemId);
}
