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

import javax.validation.Valid;


@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuthService authService;

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
    public String viewProject(@PathVariable("uuid") String uuid, ModelMap modelMap){
        Project project = projectService.getProjectUuid(uuid);
        if (project != null){
            modelMap.addAttribute("project", project);
            return "viewProject";
        }else
            return "redirect:/profile";
    }
}
