package com.example.group4project.response;

import com.example.group4project.entities.Team;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class ResponseMessage {

    private String message;
    private String url;
    private String error = "";
    private List<Team> teams = new ArrayList<Team>();

    public ResponseMessage(String message, String url, List<Team> teams) {
        this.message = message;
        this.url = url;
        this.teams = teams;
    }

    public ResponseMessage(String message, String url, String error) {
        this.message = message;
        this.url = url;
        this.error = error;
    }

    public ResponseMessage(String message, String url) {
            this.message=message;
            this.url=url;
        }

    public void setMessage(String message) {
            this.message = message;
        }

    public String getMessage() {
            return this.message;
        }

    public void setUrl(String url) {
            this.url = url;
        }

    public String getUrl() {
        return this.url;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public static class RetrospectiveItem {

        @Id
        private String id;
        private String teamId;      // item belongs to team with this id
        private int sprintNum;      // item refers to something from this sprint
        private String category;    // mad, glad or sad

        private String relatesTo;   // type of work or task - for grouping related items

        private String description;
        private List<String> comments;

        public RetrospectiveItem() {}

        public RetrospectiveItem(String teamId, int sprintNum, String category, String relatesTo,
                                 String description, String comment) {
            this.teamId = teamId;
            this.sprintNum = sprintNum;
            this.category = category;
            this.relatesTo = relatesTo;
            this.description = description;

            comments = new ArrayList<>();
            if(comment != "") {
                comments.add(comment);
            }
        }

        public String getId() { return id; }

        public void setId(String id) { this.id = id; }

        public String getTeamId() { return teamId; }

        public void setTeamId(String teamId) { this.teamId = teamId; }

        public int getSprintNum() { return sprintNum; }

        public void setSprintNum(int sprintNum) { this.sprintNum = sprintNum; }

        public String getCategory() { return category; }

        public void setCategory(String category) { this.category = category; }

        public String getRelatesTo() { return relatesTo; }

        public void setRelatesTo(String relatesTo) { this.relatesTo = relatesTo; }

        public String getDescription() { return description; }

        public void setDescription(String description) { this.description = description; }

        public List<String> getComments() { return comments; }

        public void setComments(List<String> comments) { this.comments = comments; }


        // need to test these methods in user interface

        public List<String> addComment(String comment) {
            this.comments.add(comment);
            return this.comments;
        }

        public List<String> deleteComment(String comment) {
            if(this.comments.contains(comment)) {
                this.comments.remove(comment);
            }
            return this.comments;
        }
    }
}
