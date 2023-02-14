package dataaccess;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Account;
import models.Client;

public class ClientDB {
    public List<Client> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Client> clients = em.createNamedQuery("Client.findAll", Client.class).getResultList();
            return clients;
            
        } finally {
            em.close();
        }
    }
    public Client get(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Account account = em.find(Account.class, username);
            List<Client> clientList = account.getClientList();
            Client client = clientList.get(0);
            return client;
            
        } finally {
            em.close();
        }
    }
    public Client get(int clientId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Client client = em.find(Client.class, clientId);
            return client;
        } finally {
            em.close();
        }
    }
    
    public void insert(Client client) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        getAll().add(client);
        
        try {
            et.begin();
            em.persist(client);
            em.merge(client);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    public void delete(Client client) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        getAll().remove(client);
        
        try {
            et.begin();
            em.remove(em.merge(client));
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update(Client client) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(client);
            trans.commit();
        } catch(Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
