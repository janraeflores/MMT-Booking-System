package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Appointment;
import models.Account;

public class AppointmentDB {
    /**
     * Gets all the Appointments in the MMTDB database
     * 
     * @return list of appointments 
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Appointment> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Appointment.findAll", Appointment.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets all appointments based on the provided username
     * 
     * @param username associated with the appointment
     * @return list of all appointments with specified username
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Appointment> getAll(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Account account = em.find(Account.class, username);
            return account.getAppointmentList();
            
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets appointment based on specified appointment ID
     * 
     * @param appointmentId unique ID for appointment
     * @return appointment based on specified ID
     * @throws Exception if an error occurs while accessing the database
     */
    public Appointment get(int appointmentId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Appointment appt = em.find(Appointment.class, appointmentId);
            return appt;
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts a new appointment into the database
     * 
     * @param appt object that holds all the details of an appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public void insert(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Account account = appt.getAccount();
            account.getAppointmentList().add(appt);
            
            et.begin();
            em.persist(appt);
            em.merge(account);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * Updates an appointments details
     * 
     * @param appt object that holds the appointment details
     * @throws Exception if an error occurs while accessing the database
     */
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
    /**
     * Deletes an appointment
     * 
     * @param appt appointment object
     * @throws Exception if an error occurs while accessing the database
     */
    public void delete(Appointment appt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            Account account = appt.getAccount();
            account.getAppointmentList().remove(appt);
            
            et.begin();
            em.remove(em.merge(appt));
            em.merge(account);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }
}
