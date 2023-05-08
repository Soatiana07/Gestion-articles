package com.example.springmvc.controller;

import com.example.springmvc.model.Admin;
import com.example.springmvc.service.AdminService;
import com.example.springmvc.service.ContenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    ContenuService contenuService;

    @GetMapping("/login")
    public String afficherlogin(Model model){
        model.addAttribute("log",new Admin());
        return "loginAdmin";
    }

    @GetMapping
    public ModelAndView login(@ModelAttribute Admin admin, @RequestParam(name = "offset",defaultValue = "0") Integer offset,
                              @RequestParam(name="limit",defaultValue = "4") Integer limit) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Admin> ad = adminService.findByEmailAndPassword(admin);
        if (ad.size() != 0){
            modelAndView.addObject("listecree",contenuService.findContenusCreeWithPagination(offset,limit));
            modelAndView.addObject("offset",offset);
            modelAndView.addObject("limit", limit);
            modelAndView.setViewName("listeContenu");
        }
        else {
            modelAndView.setViewName("loginAdmin");
            modelAndView.addObject("Erreur", "Your email or password is incorrect.");
        }
        return modelAndView;
    }
}
