package com.training.online_shopping.controller;

import com.training.online_shopping.common.UserModel;
import com.training.online_shopping.service.ConstantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ConstantService.USER)
public class IndexController {

    @GetMapping({"/","/home.htm"})
    public String index(Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickHome",true);
        model.addAttribute(ConstantService.TITLE, "Home");
        return "page";
    }

    @GetMapping({"/login","/user.htm"})
    public String loginUser(Model model,@ModelAttribute("message") String message){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute(ConstantService.TITLE, "Login Page");
        model.addAttribute("userClickUser",true);
        model.addAttribute(ConstantService.ACTION,"customer/login-validate");
        model.addAttribute(ConstantService.COMMAND,new UserModel());
        if(message!=null){
            model.addAttribute(ConstantService.MESSAGE,message+"");
        }
        return "page";
    }

    @GetMapping("/error")
    public String error(){
        return "error.jsp";
    }

    @GetMapping({"/signup","/registerPanel.htm"})
    public String signup(Model model, @ModelAttribute("message") String message){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute(ConstantService.TITLE, "Signup Page");
        model.addAttribute("userClickRegister",true);
        model.addAttribute(ConstantService.ACTION,"customer/signup-add");
        model.addAttribute(ConstantService.COMMAND,new UserModel());
        if(message!=null){
            model.addAttribute(ConstantService.MESSAGE,message+"");
        }
        return "page";
    }

    @GetMapping({"/adminPanel.htm"})
    public String adminUser(@ModelAttribute("message")String message,Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute(ConstantService.TITLE, "Admin Page");
        model.addAttribute("userClickAdmin",true);
        model.addAttribute(ConstantService.ACTION,"admin/admin-validate");
        model.addAttribute(ConstantService.COMMAND,new UserModel());
        if(message!=null){
        	model.addAttribute(ConstantService.MESSAGE, message);
        }
        return "page";
    }
}
