package services;

import dataaccess.AppointmentDB;
import dataaccess.ServiceDB;
import java.util.Date;
import java.util.List;
import models.Appointment;
import models.Client;
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
    
    public void insert(Client client, int serviceId, String appointmentAddress, Date appointmentDate) throws Exception {
        ServiceDB servdb = new ServiceDB();
        AppointmentDB apptdb = new AppointmentDB();
     
        Service service = servdb.getService(serviceId);
        Appointment appt = new Appointment(0, client, service, true);
        
        appt.setAppointmentAddress(appointmentAddress);
        appt.setAppointmentDate(appointmentDate);
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
