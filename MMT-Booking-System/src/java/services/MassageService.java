package services;

import dataaccess.ServiceDB;
import java.util.List;
import models.Service;

/**
 *
 * @author Joanna
 */
public class MassageService {
    
    public List<Service> getAll() throws Exception {
        ServiceDB servdb = new ServiceDB();
        
        return servdb.getAll();
    }
    public Service get(int serviceId) throws Exception {
        ServiceDB servdb = new ServiceDB();
        
        return servdb.getService(serviceId);
    }
    public void insert(String type, String description, double cost) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = new Service(0, type, description, cost);
        
        servdb.insert(service);
    }
    public void update(int serviceId, String type, String description, double cost) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = servdb.getService(serviceId);
        
        service.setServiceType(type);
        service.setServiceDesc(description);
        service.setServiceCost(serviceId);
        
        servdb.update(service);
    }
    public void delete(int serviceId) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = servdb.getService(serviceId);
        
        servdb.delete(service);
    }
}
