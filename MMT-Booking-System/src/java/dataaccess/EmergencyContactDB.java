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
    
    /**
     * Gets all emergency contacts in the database
     * 
     * @return a list of all emergency contacts as a List<EmergencyContact>
     * @throws Exception if there is a database connection error
     */
    public List<EmergencyContact> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<EmergencyContact> ecList = em.createNamedQuery("EmergencyContact.findAll", EmergencyContact.class).getResultList();
            return ecList;
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets all emergency contacts associated with an account.
     * 
     * @param username of the account
     * @return a list of all emergency contacts associated with an account as
     *         a List<EmergencyContact>
     * @throws Exception if there is a database connection error
     */
    public List<EmergencyContact> getAll(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
           Account account = em.find(Account.class, username);
           return account.getEmergencyContactList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets an emergency contact in the database with the associated 
     * primary key.
     * 
     * @param ecId unique identifier of the emergency contact
     * @return an emergency contact with the associated id
     * @throws Exception if there is a database connection error or
     *         there is no entry in the database
     */
    public EmergencyContact get(int ecId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            EmergencyContact ec = em.find(EmergencyContact.class, ecId);
            return ec;
        } finally {
            em.close();
        }
    }
    
    /**
     * Adds a new emergency contact entry in the database.
     * 
     * @param ec
     * @throws Exception 
     */
    public void insert(EmergencyContact ec) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Account account = ec.getFkAccount();
            account.getEmergencyContactList().add(ec);
            
            et.begin();
            em.persist(ec);
            em.merge(account);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * @param ec
     * @throws Exception 
     */
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
    
    /**
     * 
     * @param ec
     * @throws Exception 
     */
    public void delete(EmergencyContact ec) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
  
        try {
            Account account = ec.getFkAccount();
            account.getEmergencyContactList().remove(ec);
            
            et.begin();
            em.remove(em.merge(ec));
            em.merge(account);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    
}
