package com.example.springmvc.controller;

import com.example.springmvc.dao.HibernateDAO;
import com.example.springmvc.model.Admin;
import com.example.springmvc.model.Contenu;
import com.example.springmvc.service.ContenuService;
import com.example.springmvc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class ContenuController {
    @Autowired
    ContenuService contenuService;

    @Autowired
    HibernateDAO hibernateDAO;
    @Autowired
    TypeService typeService;

    @GetMapping("/")
    public ModelAndView afficherChoix(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listetype",typeService.getAllType());
        modelAndView.setViewName("choixcontenu");
        return modelAndView;
    }

    @PostMapping ("/apreschoix")
    public String afficherApresChoix(Model model, HttpServletRequest request) throws Exception{
        String redirect = "";
        int idtype = Integer.parseInt(request.getParameter("idtype"));
        if (idtype == 1){
            redirect = "insertionArticle";
            model.addAttribute("idtype", idtype);
        } else if (idtype == 2) {
            redirect = "insertionEvenement";
            model.addAttribute("idtype", idtype);
        }
        return redirect;
    }

    @PostMapping ("/insertion")
    public ModelAndView insertContenu(@ModelAttribute Contenu contenu, HttpServletRequest request , @RequestParam CommonsMultipartFile file, HttpSession session) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        int idtype = Integer.parseInt(request.getParameter("idtype"));
        String path = session.getServletContext().getRealPath("/images");
        String fileName = contenuService.uploadPhoto(path, file);
        contenu.setVisuel(fileName);
        contenu.setType(idtype);
        contenu.setStatus(0);
        contenuService.insertContenu(contenu);
        modelAndView.addObject("listecree",contenuService.getContenuCreeSansDatePub());
        modelAndView.setViewName("listeContenuAuteur");
        return modelAndView;
    }

    @PostMapping("/updatedate/{id}")
    public String ajouterDatePub(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
        String dateAsString = request.getParameter("datepublication");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAsString, formatter);
        contenuService.updateContenu(id,dateTime);
        return "redirect:/apublier"; // mbola tsy mety
    }

    @GetMapping("/formulairemodif/{id}")
    public String modificationContenu(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
        Contenu contenu = contenuService.getById(id);
        request.setAttribute("contenuById", contenu);
        return "formulaireModification";
    }

    @PostMapping("/modifier/{id}")
    public String modifierContenu(Model model, @PathVariable("id") Integer id, @ModelAttribute Contenu contenu, @RequestParam CommonsMultipartFile file, HttpSession session) throws Exception {
        String path = session.getServletContext().getRealPath("/images");
        String fileName = contenuService.uploadPhoto(path, file);
        contenu.setVisuel(fileName);

        contenuService.updateContenu(contenu);

        model.addAttribute("listecree", contenuService.getContenuCreeSansDatePub());
        return "listeContenuAuteur";
    }

//    @GetMapping("/apublier")
//    public ModelAndView getContenuAPublier(){
//        ModelAndView modelAndView = new ModelAndView();
//        List<Contenu> apub = contenuService.getContenuAPublier();
//        modelAndView.addObject("listeapub", apub);
//        modelAndView.setViewName("listeAvecDatePub");
//        return modelAndView;
//    }

    @GetMapping("/apublier")
    public String getContenuAPublier(Model model, @RequestParam(name = "offset",defaultValue = "0") Integer offset,
                                           @RequestParam(name="limit",defaultValue = "4") Integer limit){
        List<Contenu> results = contenuService.findContenusAPublierWithPagination(offset, limit);
        model.addAttribute("listeapub", results);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        return "listeAvecDatePub";
    }

    @PostMapping("/publier/{id}")
    public String publier(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
        contenuService.publier(id, 1);
        return "redirect:/apublier"; // mbola tsy mety
    }

//    @GetMapping("/frontoffice")
//    public ModelAndView getContenusAffiches(){
//        ModelAndView modelAndView = new ModelAndView();
//        List<Contenu> liste = contenuService.getAAfficher();
//        modelAndView.addObject("listeAfficher",liste);
//        modelAndView.setViewName("accueilClient");
//        return modelAndView;
//    }

    @GetMapping("/frontoffice")
    public String getFrontOffice(Model model, @RequestParam(name = "offset",defaultValue = "0") Integer offset,
                                   @RequestParam(name="limit",defaultValue = "4") Integer limit) {
//        List<Contenu> results = contenuService.findContenusPublieWithPagination(offset, limit);
        List<Contenu> results = contenuService.getPriorisesPagines(offset, limit);
        model.addAttribute("listeAfficher", results);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        return "accueilClient";
    }

    @GetMapping("/backoffice")
    public String getBackoffice(Model model, @RequestParam(name = "offset",defaultValue = "0") Integer offset,
                                @RequestParam(name="limit",defaultValue = "4") Integer limit){
        List<Contenu> results = contenuService.findContenusPublieWithPagination(offset, limit);
        model.addAttribute("listeAfficher", results);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        return "listeContenuPublie";
    }

    @GetMapping("/prioriser")
    public String prioriser(HttpServletRequest request) throws Exception {
        String[] aprioriser = request.getParameterValues("priorite");
//            System.out.println("Tafiditraaa");
        for (int i = 0; i < aprioriser.length; i++) {
            System.out.println("Valeurs : "+aprioriser[i]);
            System.out.println("INTTTTT : "+Integer.parseInt(aprioriser[i]));
//            contenuService.prioriserContenu(Integer.parseInt(aprioriser[i]), 1);
        }
        return "redirect:/backoffice";
    }

    @GetMapping("/recherche")
    public String searchFrontOffice(Model model,@RequestParam(name = "offset",defaultValue = "0") Integer offset,
                                    @RequestParam(name="limit",defaultValue = "4") Integer limit, HttpServletRequest request){
        String search = request.getParameter("search");
        List<Contenu> result = Collections.emptyList();
        Integer count = 0;
        if (!StringUtils.isEmpty(search)) {
            result = contenuService.searchFrontOffice(search, offset, limit);
            count = contenuService.countResults(search);
        }
        int currentPage = offset / limit;
        int totalPages = (int) Math.ceil((double) count / limit);
        boolean hasNext = currentPage < totalPages - 1;
        boolean hasPrev = currentPage > 0;
        model.addAttribute("result", result);
        model.addAttribute("search", search);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("hasPrev", hasPrev);

        return "resultatrecherche";
    }
}
