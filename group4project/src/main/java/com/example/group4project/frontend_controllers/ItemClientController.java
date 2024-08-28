package com.example.group4project.frontend_controllers;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Item;
import com.example.group4project.entities.Retrospective;
import com.example.group4project.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemClientController {

    @Autowired
    RepositoryDAO repositoryDAO;

    @RequestMapping("/showItem")
    public String showItem(Model model, @RequestParam("itemId") String itemId,
                           @RequestParam("retroId") String rId,
                           @RequestParam("teamId") String teamId) {


        Item item = repositoryDAO.getByItemId(itemId);
        model.addAttribute("item", item);
        model.addAttribute("retroId", rId);
        model.addAttribute("teamId", teamId);

        Retrospective r = repositoryDAO.getRetrospectiveById(rId);
        String retroName = r.getName();
        model.addAttribute("retroName", retroName);

        List<String> comments = item.getComments();
        model.addAttribute("comments", comments);

        return "displayItem";
    }


    @RequestMapping("/addCommentToItem")
    public String addCommentToItem(Model model, @RequestParam("itemId") String itemId,
                           @RequestParam("retroId") String rId,
                           @RequestParam("teamId") String teamId) {


        Item item = repositoryDAO.getByItemId(itemId);
        model.addAttribute("itemId", itemId);
        model.addAttribute("retroId", rId);
        model.addAttribute("teamId", teamId);

        Retrospective r = repositoryDAO.getRetrospectiveById(rId);
        String retroName = r.getName();
        model.addAttribute("retroName", retroName);

        return "addComment";
    }

    // submitComment

    @RequestMapping("/submitComment")
    public String submitComment(Model model, @RequestParam("itemId") String itemId,
                                   @RequestParam("retroId") String rId,
                                   @RequestParam("teamId") String teamId,
                                   @RequestParam("comment") String comment) {

        model.addAttribute("retroId", rId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("itemId", itemId);
        model.addAttribute("comment", comment);

        Item item = repositoryDAO.getByItemId(itemId);
        List<String> comments = item.getComments();
        comments.add(comment);
        item.setComments(comments);

        repositoryDAO.createItem(item);

        return "commentConfirmation";
    }

    @RequestMapping("/createNewItem")
    public String createNewItem(@RequestParam("retroId") String retroId,
                                @RequestParam("teamId") String teamId,
                           @RequestParam("category") String category,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description,
                           Model model) {

        model.addAttribute("teamId", teamId);
        model.addAttribute("retroId", retroId);

        String teamName="";

        Optional<Team> teamOpt = repositoryDAO.getTeamById(teamId);
        if(teamOpt.isPresent()) {
            Team t = teamOpt.get();
            teamName = t.getName();
            model.addAttribute("teamName", teamName);
        }

        Retrospective retrospective = repositoryDAO.getRetrospectiveById(retroId);
        String retroName = retrospective.getName();
        model.addAttribute("retroName", retroName);

        Item i = new Item(retroId, category, title, description);
        i.setTeamName(teamId);
        i.setTeamName(teamName);
        i.setRetroName(retroName);
        Item newItem = repositoryDAO.createItem(i);

        model.addAttribute("item", newItem);

        return "newItemConfirmation";
    }

    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("itemId") String itemId,
                             @RequestParam("retroId") String retroId,
                             @RequestParam("teamId") String teamId,
                             Model model) {

        model.addAttribute("retroId", retroId);
        model.addAttribute("teamId", teamId);

        Optional<Team> teamOpt = repositoryDAO.getTeamById(teamId);
        if(teamOpt.isPresent()) {
            Team t = teamOpt.get();
            String teamName = t.getName();
            model.addAttribute("teamName", teamName);
        }

        Retrospective retrospective = repositoryDAO.getRetrospectiveById(retroId);
        String retroName = retrospective.getName();
        model.addAttribute("retroName", retroName);

        Item item = repositoryDAO.deleteItem(itemId);
        model.addAttribute("item", item);

        return "successfulItemDeletion";
    }

    @RequestMapping("/getAllRetrospectiveItems")
    public String getAllRetrospectiveItems(Model model) {

        List<Item> allItems = repositoryDAO.getAllItems();

        model.addAttribute("allItems", allItems);

        return "allItems";
    }

    @RequestMapping("/filterItems")
    public String filterItems(@RequestParam("teamName") String teamName,
                              @RequestParam("category") String category,
                              Model model) {

        model.addAttribute("teamNameQuery", teamName);
        model.addAttribute("categoryQuery", category);

        List<Item> allItems = repositoryDAO.getAllItems();
        List<Item> filteredItems = new ArrayList<>();
        if(category.equals("All Categories")) {
            filteredItems = allItems;
        }
        else {
            filteredItems = repositoryDAO.findItemsByCategory(category);
        }

        if(teamName==null || teamName=="") {
            model.addAttribute("filteredItems", filteredItems);
        }
        else {
            List<Item> finalFilteredItems = new ArrayList<>();
            for(Item i: filteredItems) {
                String itemsTeamName = i.getTeamName();
                if(itemsTeamName.toLowerCase().contains(teamName.toLowerCase())) {
                    finalFilteredItems.add(i);
                }
            }
            model.addAttribute("filteredItems", finalFilteredItems);
        }

        return "filteredItems";
    }

}
