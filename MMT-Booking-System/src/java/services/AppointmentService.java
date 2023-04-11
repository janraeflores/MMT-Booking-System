package services;

import dataaccess.AppointmentDB;
import dataaccess.ServiceDB;
import java.util.Date;
import java.util.List;
import models.Appointment;
import models.Account;
import models.Service;

public class AppointmentService {
    
    public List<Appointment> getAll() throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        
        return apptdb.getAll();
    }
    // get all appointments associated with specific client
    public List<Appointment> getAll(String username) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        
        return apptdb.getAll(username);
    }
    public Appointment get(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        return appt;
    }
    
    public void insert(int serviceId, Account account, String appointmentAddress, Date appointmentDate, int duration) throws Exception {
        ServiceDB servdb = new ServiceDB();
        AppointmentDB apptdb = new AppointmentDB();
     
        Service service = servdb.get(serviceId);
        Appointment appt = new Appointment(0, appointmentAddress, appointmentDate, true, duration);
        
        appt.setAccount(account);
        appt.setAppointmentDate(appointmentDate);
        appt.setService(service);
        apptdb.insert(appt);
    }
    
    public void delete(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        
        apptdb.delete(appt);
    }
    
    public void update(int appointmentId, Date appointmentDate, String address, String additionalInfo) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        
        appt.setAppointmentDate(appointmentDate);
        appt.setAppointmentAddress(address);
        appt.setAdditionalInfo(additionalInfo);
        
        apptdb.update(appt);
    }
    
    public void cancel(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        appt.setStatus(false);
        apptdb.update(appt);
    }
}
