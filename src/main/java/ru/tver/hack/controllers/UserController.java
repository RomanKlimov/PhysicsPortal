package ru.tver.hack.controllers;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;
import ru.tver.hack.security.Role.Role;
import ru.tver.hack.services.interfaces.AuthService;
import ru.tver.hack.services.interfaces.ProjectService;
import ru.tver.hack.services.interfaces.UserService;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addSkillToUser")
    public ResponseEntity addNewSkill(@RequestParam("skillName") String skillName, Authentication authentication){
        if (authentication != null){
            User user = authService.getUserByAuthentication(authentication);
            userService.addSkillToUser(skillName, user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication,  ModelMap modelMap){
        if (authentication !=null) {
            User user = authService.getUserByAuthentication(authentication);
            List<Project> projectsByMember = projectService.getProjectsByMember(user);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("memberOfProjects", projectsByMember);
            if (user.getRole().equals(Role.USER))
                return "profile";
        }
        return "redirect:/";
    }

    @GetMapping("user/profile/{userEmail:.+}")
    public String getUserProfileForView(@PathVariable("userEmail") String userEmail, ModelMap modelMap){
        User user = userService.getUserByEmail(userEmail);
        List<Project> projectsByMember = projectService.getProjectsByMember(user);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("memberOfProjects", projectsByMember);
        return "guestProfile";
    }

    @GetMapping("/contacts")
    public String getContactsPage(){
        return "contacts";
    }

    @GetMapping("/meetups")
    public String getMeetupsPage(){
        return "meetups";
    }

    @GetMapping("/teams")
    public String getTeamsPage(){
        return "teams";
    }
}
