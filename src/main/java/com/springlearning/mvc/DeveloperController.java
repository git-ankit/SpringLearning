package com.springlearning.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRep;

    @Autowired
    SkillRepository skillRep;

    @RequestMapping("/")
    public String developer(Model model){
        model.addAttribute("developers", developerRep.findAll());
        return "homepage";
    }


    @RequestMapping("/developer/{id}")
    public String developer(@PathVariable Long id, Model model){
        model.addAttribute("developer", developerRep.findById(id).orElse(new Developer("Bob", "Brown", "brown@example.com",
                Arrays.asList())));
        model.addAttribute("skills", skillRep.findAll());
        return "developer";
    }

}
