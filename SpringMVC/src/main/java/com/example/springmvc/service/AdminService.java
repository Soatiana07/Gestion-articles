package com.example.springmvc.service;

import com.example.springmvc.dao.HibernateDAO;
import com.example.springmvc.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    HibernateDAO hibernateDao;

//    Login admin
    public List<Admin> findByEmailAndPassword(Admin admin) {
        return hibernateDao.findWhere(admin);
    }
}

