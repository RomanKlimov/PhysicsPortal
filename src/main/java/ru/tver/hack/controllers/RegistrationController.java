package ru.tver.hack.controllers;


import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tver.hack.exceptions.EmailExistsException;
import ru.tver.hack.form.UserRegistrationForm;
import ru.tver.hack.models.User;
import ru.tver.hack.security.Role.Role;
import ru.tver.hack.services.interfaces.MapInfoService;
import ru.tver.hack.services.interfaces.RegistrationService;
import ru.tver.hack.validator.UserRegistrationFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegistrationController  {

    @Autowired
    private MapInfoService mapInfoService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;


    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder){
        binder.addValidators(userRegistrationFormValidator);
    }

    @PostMapping("/signUp")
    public String registerUserAccount(@ModelAttribute("userForm") @Valid UserRegistrationForm userRegistrationForm,
                                      BindingResult result,  RedirectAttributes attributes) throws EmailExistsException, InterruptedException, IOException, UnirestException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("userRegistrationForm", userRegistrationForm);
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        registrationService.createUserAccount(userRegistrationForm);
        mapInfoService.addCity(userRegistrationForm.getCity());
        return "redirect:/login";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        if (!model.containsAttribute("userRegistrationForm")){
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        }
        return "signUp";
    }

    @GetMapping("/signUp1")
    public String signUp1(){
        return "signUp1";
    }
}
