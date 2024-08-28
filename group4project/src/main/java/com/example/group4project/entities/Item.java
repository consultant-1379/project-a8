package com.example.group4project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection="items")
public class Item {

    @Id
    private String itemId;
    @Field("retroId")
    private String retroId;
    private String category;
    private String title;
    private String description;
    private List<String> comments;

    private String teamId;
    private String teamName;

    private String retroName;


    public Item(String retroId, String category, String title, String description) {
        this.retroId = retroId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRetroName() {
        return retroName;
    }

    public void setRetroName(String retroName) {
        this.retroName = retroName;
    }

    public void addCommentToItem(String comment) {
        if(comment != null && comment != "") {
            this.comments.add(comment);
        }
    }

    public String getItemId() {
        return itemId;
    }


    public String getRetroId() {
        return retroId;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
