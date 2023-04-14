package services;

import dataaccess.AccountDB;
import dataaccess.AppointmentDB;
import dataaccess.ServiceDB;
import java.util.Date;
import java.util.List;
import models.Appointment;
import models.Account;
import models.Service;

public class AppointmentService {
    
    /**
     * Gets all appointments in the MMTDB database
     * 
     * @return list of all appointments
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Appointment> getAll() throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        return apptdb.getAll();
    }
    
    /**
     * Gets all appointments associated to an account
     * 
     * @param username username associated with the appointment
     * @return all appointments with the specific username
     * @throws Exception if there is an error while accessing the database
     */
    public List<Appointment> getAll(String username) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        return apptdb.getAll(username);
    }
    
    /**
     * Gets specific appointment associated to an appointment ID
     * 
     * @param appointmentId unique identifier for appointment
     * @return specific appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public Appointment get(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        return appt;
    }
    
    /**
     * Inserts new appointment
     * 
     * @param serviceId type of service
     * @param username patient accounts username
     * @param appointmentAddress location of appointment
     * @param appointmentDate date of appointment
     * @param duration length of appointment
     * @param additionalInfo extra information for appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public void insert(int serviceId, String username, String appointmentAddress, Date appointmentDate, int duration, String additionalInfo) throws Exception {
        ServiceDB servdb = new ServiceDB();
        AppointmentDB apptdb = new AppointmentDB();
        AccountDB accountdb = new AccountDB();
        Account account = accountdb.get(username);
     
        Service service = servdb.get(serviceId);
        Appointment appt = new Appointment(0, appointmentAddress, appointmentDate, true, duration);
        
        appt.setAccount(account);
        appt.setAppointmentDate(appointmentDate);
        appt.setService(service);
        appt.setAdditionalInfo(additionalInfo);
        
        account.setMedicalInfo(additionalInfo);
   
        apptdb.insert(appt);
    }
    
    /**
     * Deletes appointment from MMTDB
     * 
     * @param appointmentId unique identifier of appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public void delete(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        
        apptdb.delete(appt);
    }
    
    /**
     * Updates appointment
     * 
     * @param appointmentId unique identifier of appointment
     * @param appointmentDate date of appointment
     * @param address of appointment
     * @param additionalInfo extra information about appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public void update(int appointmentId, Date appointmentDate, String address, String additionalInfo) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        
        appt.setAppointmentDate(appointmentDate);
        appt.setAppointmentAddress(address);
        appt.setAdditionalInfo(additionalInfo);
        
        apptdb.update(appt);
    }
    
    /**
     * Allows patient to cancel appointment. Does not remove from MMTDB database.
     * 
     * @param appointmentId unique identifier of appointment
     * @throws Exception if an error occurs while accessing the database
     */
    public void cancel(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        appt.setStatus(false);
        
        apptdb.update(appt);
    }
}
