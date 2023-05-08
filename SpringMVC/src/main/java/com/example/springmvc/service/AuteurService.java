package com.example.springmvc.service;

import com.example.springmvc.dao.HibernateDAO;
import com.example.springmvc.model.Admin;
import com.example.springmvc.model.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {
    @Autowired
    HibernateDAO hibernateDAO;

    //    Login auteur
    public List<Auteur> findByEmailAndPassword(Auteur a) {
        return hibernateDAO.findWhere(a);
    }
}
