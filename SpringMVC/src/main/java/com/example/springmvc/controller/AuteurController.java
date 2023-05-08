package com.example.springmvc.controller;

import com.example.springmvc.model.Admin;
import com.example.springmvc.model.Auteur;
import com.example.springmvc.service.AdminService;
import com.example.springmvc.service.AuteurService;
import com.example.springmvc.service.ContenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {
    @Autowired
    AuteurService adminService;

    @Autowired
    ContenuService contenuService;

    @GetMapping("/login")
    public String afficherlogin(Model model){
        model.addAttribute("log",new Auteur());
        return "loginAuteur";
    }

    @PostMapping
    public String login(@ModelAttribute Auteur auteur, Model model) throws Exception{
        String redirect = "";
        ModelAndView modelAndView = new ModelAndView();
        List<Auteur> ad = adminService.findByEmailAndPassword(auteur);
        if (ad.size() != 0){
            redirect = "/";
        }
        else {
            redirect = "/auteurs/login";
            model.addAttribute("Erreur", "Your email or password is incorrect.");
        }
        return "redirect:"+redirect;
    }
}
