package ru.tver.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tver.hack.models.MapInfo;
import ru.tver.hack.services.interfaces.MapInfoService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MapInfoService mapInfoService;

    @Transactional
    @GetMapping("/index")
    public String getHomePage(ModelMap modelMap){
        List<MapInfo> allMapInfo = mapInfoService.getAll();
        modelMap.addAttribute("mapInfo", allMapInfo);
        return "index";
    }

}
