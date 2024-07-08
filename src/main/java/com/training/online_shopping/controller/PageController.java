package com.training.online_shopping.controller;

import com.training.online_shopping.service.ConstantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/","/home.htm","/index.htm"})
    public String index(Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickHome",true);
        model.addAttribute(ConstantService.TITLE, "Home");
        return "page";
    }


}

// controller=data+view
