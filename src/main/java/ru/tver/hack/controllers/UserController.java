package ru.tver.hack.controllers;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tver.hack.models.Event;
import ru.tver.hack.models.Project;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tver.hack.models.FileInfo;
import ru.tver.hack.models.User;
import ru.tver.hack.security.Role.Role;
import ru.tver.hack.services.implementations.FileInfoServiceImpl;
import ru.tver.hack.services.interfaces.AuthService;
import ru.tver.hack.services.interfaces.EventService;
import ru.tver.hack.services.interfaces.ProjectService;
import ru.tver.hack.services.interfaces.UserService;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FileInfoServiceImpl fileInfoService;

    @Autowired
    private EventService eventService;

    @PostMapping("/addSkillToUser")
    public ResponseEntity addNewSkill(@RequestParam("skillName") String skillName, Authentication authentication){
        if (authentication != null){
            User user = authService.getUserByAuthentication(authentication);
            userService.addSkillToUser(skillName, user);
            List<Event> nearEvents = eventService.getNearEvents();
            System.out.println(nearEvents);
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


    @PostMapping(value = "/addPhoto", consumes = "multipart/form-data")
    public String addPhoto( Authentication authentication,  ModelMap modelMap, RedirectAttributes attributes,
                           @RequestParam("file") MultipartFile file){
        if (authentication != null) {
            User user = authService.getUserByAuthentication(authentication);
            if (file.getSize()>0){
                FileInfo fileInfo = fileInfoService.savePicture(file);
                user.setImageUrl(fileInfo.getFileName());
                userService.saveUser(user);
                System.out.println("TEST SET IMG");
            }
            System.out.println("FUCK THAT");
            return "redirect:/profile";
        }
        return "redirect:/";
    }

    @GetMapping("/photo/{fileName:.+}")
    public void getPicture(@PathVariable("fileName") String fileName, HttpServletResponse response){
        fileInfoService.getPicture(fileName, response);
    }


}
