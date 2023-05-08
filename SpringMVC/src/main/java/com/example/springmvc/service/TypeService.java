package com.example.springmvc.service;

import com.example.springmvc.dao.HibernateDAO;
import com.example.springmvc.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    HibernateDAO hibernateDAO;

//    Liste type
    public List<Type> getAllType(){
        return hibernateDAO.getAll(Type.class);
    }
}
