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
    public List<Appointment> getAll(int clientId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        
        return apptdb.getAll(clientId);
    }
    public Appointment get(int appointmentId) throws Exception {
        AppointmentDB apptdb = new AppointmentDB();
        Appointment appt = apptdb.get(appointmentId);
        return appt;
    }
    
    public void insert(int serviceId, Account account, String appointmentAddress, Date appointmentDate, Date startTime, Date endTime) throws Exception {
        ServiceDB servdb = new ServiceDB();
        AppointmentDB apptdb = new AppointmentDB();
     
        Service service = servdb.get(serviceId);
        Appointment appt = new Appointment(0, appointmentAddress, appointmentDate, startTime, endTime, true);
        
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
    
    public void update(Date appointmentDate, Date startTime, Date endTime, String address) throws Exception {
        
    }
}
