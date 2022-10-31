package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Employee;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FrontEndController {

    @GetMapping( "/index")
    public String getIndex(Model model){ return "index"; }

    @GetMapping("/welcome")
    public String getWelcome(Model model){
        return "welcome";
    }

    //Controlador que me lleva al template de No autorizado




  /*  @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("formUser",new Employee());
        return "login";
    }*/

    @PostMapping("/login")
    public  String postLogin(@ModelAttribute("formUser") Employee employee){
        return "redirect:/index";
    }

}
