package ru.tver.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;
import ru.tver.hack.services.interfaces.AuthService;
import ru.tver.hack.services.interfaces.ProjectService;
import ru.tver.hack.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/addNewProject")
    public String addNewProject(){
        return "project";
    }

    @PostMapping("/addNewProject")
    public String addNewProject(@Valid Project project, BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "redirect:/addNewProject";
        }
        User user = authService.getUserByAuthentication(authentication);
        projectService.addNewProject(project, user);
        return "redirect:/profile";
    }

    @GetMapping("/project/{uuid}")
    public String viewProject(@PathVariable("uuid") String uuid, ModelMap modelMap,Authentication authentication){
        Project project = projectService.getProjectByUuid(uuid);
        if (project != null){
            // is user applied or not
            User user = authService.getUserByAuthentication(authentication);
            if (!project.getMembers().contains(user)){
                boolean isApplicant = false;
                if (project.getApplicants().contains(user)){
                    isApplicant= true;
                }
                modelMap.addAttribute("isApplicant", isApplicant);
            }
            if (project.getHeadOfProjectUser().equals(user)){
                modelMap.addAttribute("isAdminProject", true);
            }
            modelMap.addAttribute("project", project);
            return "viewProject";
        }else
            return "redirect:/profile";
    }

    @GetMapping("/apply/{uuid}")
    public String apply(@PathVariable("uuid") String uuid, Authentication authentication){
        User user = authService.getUserByAuthentication(authentication);
        Project project = projectService.getProjectByUuid(uuid);
        if (project != null){
            List<User> applicants = project.getApplicants();
            if (!applicants.contains(user)){
                applicants.add(user);
                projectService.updateProject(project);
            }
        }
        return "redirect:/project/"+uuid;
    }

    // accepting or declining applicant
    @GetMapping("/project/{uuid}/{userEmail}/{response}")
    public String accept(@PathVariable("uuid") String uuid, @PathVariable(value = "userEmail") String userEmail, @PathVariable("response") String response, Authentication authentication){
        User user = authService.getUserByAuthentication(authentication);
        Optional<Project> project = user.getProjects().stream().filter(project1 -> project1.getUuid().equals(uuid)).findFirst();
        if (project.isPresent()){
            Optional<User> applicant = project.get().getApplicants().stream().filter(user1 -> user1.getEmail().equals(userEmail)).findFirst();
            if (applicant.isPresent()){
                if (response.equals("accept")){
                    project.get().getMembers().add(applicant.get());
                }else if (response.equals("decline")){
                    project.get().getApplicants().remove(applicant.get());
                }
                project.get().getApplicants().remove(applicant.get());
                userService.saveUser(user);
            }
        }
        return "redirect:/project/"+uuid;
    }


}
