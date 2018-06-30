package ru.tver.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tver.hack.models.Skill;
import ru.tver.hack.services.interfaces.SkillService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date 22.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@RestController
public class SkillControllerTest {

    @Autowired
    private SkillService skillService;

    @PostMapping("/addSkill")
    public void addNewSkill( @Valid Skill skill, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors().get(0));
        }else {
            System.out.println(skill.toString());
        }
    }



    @GetMapping("/addSkill")
    public String addSkill(){
        return "addSkill";
    }

    @GetMapping("/getSkills")
    public ResponseEntity getSkills(@RequestParam("term") String query){
        List<Skill> skillsByNameLike = skillService.getSkillsByNameLike(query);
        List<String> list = skillsByNameLike.stream().map(Skill::getSkillName).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
