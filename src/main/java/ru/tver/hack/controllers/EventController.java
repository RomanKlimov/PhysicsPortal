package ru.tver.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tver.hack.models.Event;
import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;
import ru.tver.hack.services.interfaces.AuthService;
import ru.tver.hack.services.interfaces.EventService;
import ru.tver.hack.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/addNewEvent")
    public String addNewProject(){
        return "event";
    }

    @PostMapping("/addNewEvent")
    public String addNewEvent(@Valid Event event, BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "redirect:/addNewProject";
        }
        User user = authService.getUserByAuthentication(authentication);
        eventService.addNewEvent(event, user);
        return "redirect:/profile";
    }

    @GetMapping("/event/{uuid}")
    public String viewProject(@PathVariable("uuid") String uuid, ModelMap modelMap, Authentication authentication){
        Event event = eventService.getEventByUuid(uuid);
        boolean isMember = false;
        if (event != null){
            // is user applied or not
            User user = authService.getUserByAuthentication(authentication);
            if (event.getMembers().contains(user)) {
                isMember = true;
            }
            modelMap.addAttribute("isMember", isMember);
            if (event.getHeadOfEventUser().equals(user)){
                modelMap.addAttribute("isAdminEvent",true);
            }
            modelMap.addAttribute("event", event);
            return "viewEvent";
        }else
            return "redirect:/profile";
    }

    @GetMapping("/join/{uuid}")
    public String apply(@PathVariable("uuid") String uuid, Authentication authentication){
        User user = authService.getUserByAuthentication(authentication);
        Event event = eventService.getEventByUuid(uuid);
        if (event != null){
            List<User> members = event.getMembers();
            if (!members.contains(user)){
                members.add(user);
                eventService.updateEvent(event);
            }
        }
        return "redirect:/event/"+uuid;
    }


}
