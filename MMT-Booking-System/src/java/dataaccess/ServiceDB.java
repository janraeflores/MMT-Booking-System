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
    
    /**
     * Gets all provided services from MMTDB database
     * 
     * @return list of services offered
     */
    public List<Service> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.createNamedQuery("Service.findAll", Service.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets service based on specific ID given
     * 
     * @param serviceId unique identifier for type of service
     * @return type of service based on unique identifier
     * @throws Exception if an error occurs while accessing the database
     */
    public Service get(int serviceId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Service service = em.find(Service.class, serviceId);
            return service;
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts new service into MMTDB
     * 
     * @param service type of service object
     * @throws Exception if an error occurs while accessing the database
     */
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
    
    /**
     * updates the details of a service
     * 
     * @param service type of service object
     * @throws Exception if an error occurs while accessing the database
     */
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
    
    /**
     * Deletes a type of service from MMTDB database
     * 
     * @param service type of service object
     * @throws Exception if an error occurs while accessing the database
     */
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
