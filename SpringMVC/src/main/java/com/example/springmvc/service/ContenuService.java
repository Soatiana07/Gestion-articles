package com.example.springmvc.service;

import com.example.springmvc.dao.HibernateDAO;
import com.example.springmvc.model.Contenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContenuService {
    @Autowired
    HibernateDAO hibernateDAO;

//    Insertion contenu
    public void insertContenu(Contenu contenu) throws Exception{
        hibernateDAO.save(contenu);
    }

//    Upload image
    public static String uploadPhoto(String path, CommonsMultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        byte[] bytes = file.getBytes();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path +File.separator + fileName)));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        return fileName;
    }

//    Get contenus créés
    public List<Contenu> getContenuCree(){
        Contenu contenu = new Contenu();
        contenu.setStatus(0);
        return hibernateDAO.findWhere(contenu);
    }

//    Get contenus créés et qui n'ont pas encore de date de publication
    public List<Contenu> getContenuCreeSansDatePub(){
        return hibernateDAO.findContenusCree(Contenu.class);
    }

//    Get contenus validés
    public List<Contenu> getContenuPublie(){
        Contenu contenu = new Contenu();
        contenu.setStatus(1);
        return hibernateDAO.findWhere(contenu);
    }

//    Ajouter date de publication
    public void updateContenu(Integer id, LocalDateTime datepublication) throws Exception {
        hibernateDAO.updateContenu(id, datepublication);
    }

//    Get contenus a publier par l'admin
    public List<Contenu> getContenuAPublier(){
        return hibernateDAO.findContenusAPublier(Contenu.class);
    }

//    Publier un contenu (update status = 1)
    public void publier(Integer id, Integer status) throws Exception {
        hibernateDAO.updateStatus(id, status);
    }

//    Get contenus a afficher coté front-office
    public List<Contenu> getAAfficher(){
        return hibernateDAO.findContenusAffiches(Contenu.class);
    }

//    Get contenus a afficher coté front-office PAGINÉ
    public List<Contenu> findContenusPublieWithPagination(int offset, int pageSize){
        return hibernateDAO.findContenusPublieWithPagination(Contenu.class, offset, pageSize);
    }

//    Get contenus créés et qui n'ont pas encore de date de publication PAGINÉ
    public List<Contenu> findContenusCreeWithPagination(int offset, int pageSize){
        return hibernateDAO.findContenusCreeWithPagination(Contenu.class, offset, pageSize);
    }

//    Get contenus a publier par l'admin PAGINÉ
    public List<Contenu> findContenusAPublierWithPagination(int offset, int pageSize){
        return hibernateDAO.findContenusAPublierWithPagination(Contenu.class, offset, pageSize);
    }

//    Recherche par mot clé (titre, description) PAGINÉ
    public List<Contenu> searchFrontOffice(String search, Integer offset, Integer limit){
        return hibernateDAO.researchFrontOffice(Contenu.class, search, offset, limit);
    }

//    Compter résultats recherche
    public int countResults(String search){
        return hibernateDAO.countResults(Contenu.class, search);
    }

//    Modification contenu
    public void updateContenu(Contenu contenu) throws Exception {
        hibernateDAO.update(contenu);
    }

//    Get contenu by id
    public Contenu getById(Integer id) throws Exception{
        return (Contenu) hibernateDAO.findById(Contenu.class, id);
    }
//    Prioriser une contenu
    public void prioriserContenu(Integer id, Integer p) throws Exception{
        hibernateDAO.prioriser(id, p);
    }

//    Get contenus deja priorises
    public List<Contenu> getPriorisesPagines(int offset, int pageSize){
        return hibernateDAO.findContenusPubliesPriorises(Contenu.class, offset, pageSize);
    }
}
