package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Appointment;
import models.Client;

public class AppointmentDB {
    
    public List<Appointment> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Appointment.findAll", Appointment.class).getResultList();
        } finally {
            em.close();
        }
    }
    public List<Appointment> getAll(int clientId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Client client = em.find(Client.class, clientId);
            return client.getAppointmentList();
            
        } finally {
            em.close();
        }
    }
    public Appointment get(int appointmentId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Appointment appt = em.find(Appointment.class, appointmentId);
            return appt;
        } finally {
            em.close();
        }
    }
    public void insert(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Client client = appt.getClient();
            client.getAppointmentList().add(appt);
            
            et.begin();
            em.persist(appt);
            em.merge(client);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    public void update(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.merge(appt);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    public void delete(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Client client = appt.getClient();
            client.getAppointmentList().remove(appt);
            
            et.begin();
            em.remove(em.merge(appt));
            em.merge(client);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
}
