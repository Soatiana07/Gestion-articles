package com.example.springmvc.dao;

import com.example.springmvc.model.Contenu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class HibernateDAO implements InterfaceDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Object o) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            System.out.println("Saved");
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        Session session = null;
        List<T> results = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            cr.select(root);
            Query<T> query = session.createQuery(cr);
            results = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return results;
    }

    @Override
    public <T> T getById(Class<T> clazz, Integer id) throws Exception {
        Session session = null;
        T result = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            Predicate p = cb.equal(root.get("id"), id);
            addPredicatesToQuery(cb, cr, p);
            cr.select(root);
            Query<T> query = session.createQuery(cr);
            result = (T) query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return result;
    }

    @Override
    public void update(Object o) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
//            Object oth = session.getEntityManagerFactory().createEntityManager().
            session.update(o);
            tx.commit();
            System.out.println("Updated");
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
//    Zahhh
    public void updateContenu(Integer id, LocalDateTime datepublication) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Contenu article = (Contenu) session.get(Contenu.class, id);
            article.setDatepublication(datepublication);
            session.update(article);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
//  Zahhhh
    public void updateStatus(Integer id,Integer status) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Contenu article = (Contenu) session.get(Contenu.class, id);
            article.setStatus(status);
            session.update(article);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
    @Override
    public void delete(Object o) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            System.out.println("Deleted");
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public <T> List<T> getAllByPagination(Class<T> clazz, Integer offset, Integer limit) {
        Session session = null;
        List<T> results = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            cr.select(root);
            Query<T> query = session.createQuery(cr).setFirstResult(offset).setMaxResults(limit);
            results = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return results;
    }

    public <T> List<T> findWhere(T entity){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    private void addPredicatesToQuery(CriteriaBuilder cb, CriteriaQuery query, Predicate... predicates) {
        query.where(cb.and(predicates));
    }

//    BOOST
    public <T> List<T> researchFrontOffice(Class<T> clazz,/*Integer status,*/String search, Integer offset, Integer limit) {
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        Criterion title = org.hibernate.criterion.Restrictions.ilike("titre", search, MatchMode.ANYWHERE);
        Criterion description = org.hibernate.criterion.Restrictions.ilike("description", search, MatchMode.ANYWHERE);
//        Criterion statusCriterion = Restrictions.eq("status", status);
        try {
            result = session.createCriteria(clazz).add(
                    Restrictions.sqlRestriction("status=1 AND datepublication<CURRENT_TIMESTAMP")
                    )
                    .setFirstResult(offset) // offset
                    .setMaxResults(limit) // limit
                    .add(Restrictions.or(title,description))
//                    .add(statusCriterion)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

//    Zahhh
    public <T> int countResults(Class<T> type, String search) {
        int result = 0;
        Session session = this.sessionFactory.openSession();
        Criterion title = org.hibernate.criterion.Restrictions.ilike("titre", search, MatchMode.ANYWHERE);
        Criterion description = org.hibernate.criterion.Restrictions.ilike("description", search, MatchMode.ANYWHERE);
        try {
            result = ((Number) session.createCriteria(type).add(
                            Restrictions.sqlRestriction("status=1 AND datepublication<CURRENT_TIMESTAMP")
                    )
                    .setProjection(Projections.rowCount())
                    .add(Restrictions.or(title,description))
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public <T> List<T> findContenusAffiches(Class<T> classe){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe).add(
                Restrictions.sqlRestriction("status=1 AND datepublication<CURRENT_TIMESTAMP")
        ).list();
        session.close();
        return result;
    }

    public <T> List<T> findContenusAPublier(Class<T> classe){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe).add(
                Restrictions.sqlRestriction("status=0 AND datepublication is not null")
        ).list();
        session.close();
        return result;
    }

    public <T> List<T> findContenusCree(Class<T> classe){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe).add(
                Restrictions.sqlRestriction("status=0 AND datepublication is null")
        ).list();
        session.close();
        return result;
    }

//    Zah
    public <T> List<T> findContenusPublieWithPagination(Class<T> classe, Integer offset, Integer limit){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe)
                .add(Restrictions.sqlRestriction("status=1 AND datepublication<CURRENT_TIMESTAMP"))
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return result;
    }

    public <T> List<T> findContenusCreeWithPagination(Class<T> classe, Integer offset, Integer limit){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe)
                .add(Restrictions.sqlRestriction("status=0 AND datepublication is null"))
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return result;
    }

    public <T> List<T> findContenusAPublierWithPagination(Class<T> classe, Integer offset, Integer limit){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe)
                .add(Restrictions.sqlRestriction("status=0 AND datepublication is not null"))
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return result;
    }
//    Zahh
    public <T> List<T> findContenusPubliesPriorises(Class<T> classe, Integer offset, Integer limit){
        List<T> result = null;
        Session session = this.sessionFactory.openSession();
        result = session.createCriteria(classe)
                .add(Restrictions.sqlRestriction("status=1 AND datepublication<CURRENT_TIMESTAMP ORDER BY priorite DESC"))
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return result;
    }

    public void prioriser(Integer id,Integer priorite){
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Contenu article = (Contenu) session.get(Contenu.class, id);
            article.setPriorite(priorite);
            session.update(article);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    public Object findById(Class classe, int id) {
        Session ses = getSessionFactory().openSession();
        Object ob = null;
        try{
            ob = ses.get(classe, id);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ses.close();
        }
        return ob;
    }

    public void update(Object o, String where){

        Session ses = sessionFactory.openSession();
        try {
//            Transaction transaction = ses.beginTransaction();
            ses.update(where, o);
//            transaction.commit();

        } catch (Exception e) {
//            transaction.rollback();

            e.printStackTrace();
        } finally{
            ses.close();
        }

    }
}
