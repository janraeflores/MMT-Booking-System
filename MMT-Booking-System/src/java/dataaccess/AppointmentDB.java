package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Appointment;
import models.Client;

public class AppointmentDB {
    
    public void insert(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Client client = appt.getClient();
            client.getAppointmentsList().add(appt);
            
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
    public void delete(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Client client = appt.getClient();
            client.getAppointmentsList().remove(appt);
            
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
