package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Account;
import models.Role;

public class AccountDB {
    
    public List<Account> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return accounts;
        } finally {
            em.close();
        }
    }
    
    public Account get(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Account account = em.find(Account.class, username);
            return account;
        } finally {
            em.close();
        }
    }
    
    public void insert(Account account) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
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
        
    public void update(Account account, Role pRole) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            Role role = account.getRole();

            if (!pRole.equals(account.getRole())) {
                pRole.getAccountList().remove(em.merge(account));
                role.getAccountList().add(account);
            } else {
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
