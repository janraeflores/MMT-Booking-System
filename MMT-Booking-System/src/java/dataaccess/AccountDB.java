package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Account;
import models.Role;

public class AccountDB {
    
    /**
     * Gets all accounts in the MMTDB database
     * 
     * @return a list of accounts in the database as a List<Account>
     * @throws Exception
     */
    public List<Account> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return accounts;
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets the account from the MMTDB database given the username provided
     * 
     * @param username of the account
     * @return an Account with the associated username
     * @throws Exception if the username does not exist in the database
     */
    public Account get(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Account account = em.find(Account.class, username);
            return account;
        } finally {
            em.close();
        }
    }
    
    /**
     * Adds an account to the database and to the List<Account> list
     * 
     * @param account to be inserted
     * @throws Exception if the account is null, or it already exists in the database
     */
    public void insert(Account account) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().add(account);
        
        try {
            et.begin();
            em.persist(account);
            em.merge(account);
            et.commit();
            
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * Removes the given account from the database and from the List<Account> list
     * 
     * @param account to be removed
     * @throws Exception if the account does not exist in the database
     */
    public void delete(Account account) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().remove(account);
        
        try {
            et.begin();
            em.remove(em.merge(account));
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * Updates an account in the database
     * 
     * @param account to be updated
     * @throws Exception if the account does not exist in the database
     */
    public void update(Account account) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(account);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
        
    /**
     * 
     * 
     * @param account
     * @param pRole
     * @throws Exception 
     */
    public void update(Account account, Role pRole) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            Role role = account.getRole();

            if (!pRole.equals(account.getRole())) {
                pRole.getAccountList().remove(em.merge(account));
                role.getAccountList().add(account);
            } 
            else {
                role.getAccountList().remove(account);
                role.getAccountList().add(account);
            }

            trans.begin();
            em.merge(account);
            em.merge(role);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
