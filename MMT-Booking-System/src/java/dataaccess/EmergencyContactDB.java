package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Account;

import models.EmergencyContact;

/**
 *
 * @author Keith
 */
public class EmergencyContactDB {
    
    public List<EmergencyContact> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<EmergencyContact> ecList = em.createNamedQuery("EmergencyContact.findAll", EmergencyContact.class).getResultList();
            return ecList;
        } finally {
            em.close();
        }
    }
    public List<EmergencyContact> getAll(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
           Account account = em.find(Account.class, username);
            return account.getEmergencyContactList();
        } finally {
            em.close();
        }
    }
    
    public EmergencyContact get(int ecId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            EmergencyContact ec = em.find(EmergencyContact.class, ecId);
            return ec;
        } finally {
            em.close();
        }
    }
    
    public void insert(EmergencyContact ec) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.persist(ec);
            em.merge(ec);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete(EmergencyContact ec) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().remove(ec);
        
        try {
            et.begin();
            em.remove(em.merge(ec));
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update(EmergencyContact ec) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(ec);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
