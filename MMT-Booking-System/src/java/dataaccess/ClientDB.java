package dataaccess;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    public Client getClient(int clientId) {
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
}
