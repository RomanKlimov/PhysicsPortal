package ru.tver.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tver.hack.models.User;
import ru.tver.hack.services.interfaces.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String searchPage(){
        return "search";
    }

    @PostMapping("/search")
    public String searchUsers(@RequestParam("input") String input, ModelMap modelMap){
        List<User> users = userService.getUsersBySkills(input);
        modelMap.addAttribute("users", users);
        return "search";
    }
}
