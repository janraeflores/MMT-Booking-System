package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Service;

/**
 *
 * @author Joanna
 */
public class ServiceDB {
    
    public List<Service> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.createNamedQuery("Service.findAll", Service.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Service get(int serviceId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Service service = em.find(Service.class, serviceId);
            return service;
        } finally {
            em.close();
        }
    }
    public void insert(Service service) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().add(service);
        
        try {
            et.begin();
            em.persist(service);
            em.merge(service);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    public void update(Service service) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.merge(service);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    public void delete(Service service) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().remove(service);
        
        try {
            et.begin();
            em.remove(em.merge(service));
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
}
