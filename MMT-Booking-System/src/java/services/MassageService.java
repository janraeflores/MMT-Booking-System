package services;

import dataaccess.ServiceDB;
import java.util.List;
import models.Service;

/**
 *
 * @author Joanna
 */
public class MassageService {
    
    /**
     * List of all services in MMTDB
     * 
     * @return list of services
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Service> getAll() throws Exception {
        ServiceDB servdb = new ServiceDB();
        
        return servdb.getAll();
    }
    
    /**
     * Retrieves specific service based on ID
     * 
     * @param serviceId unique identifier of service
     * @return specific type of service
     * @throws Exception if an error occurs while accessing the database
     */
    public Service get(int serviceId) throws Exception {
        ServiceDB servdb = new ServiceDB();
        
        return servdb.get(serviceId);
    }
    /**
     * Inserts new type of service
     * 
     * @param type service offered
     * @param description service explanation
     * @param cost service value
     * @throws Exception if an error occurs while accessing the database
     */
    public void insert(String type, String description, double cost) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = new Service(0, type, description, cost);
        
        servdb.insert(service);
    }
    
    /**
     * Updates a specific services details
     * 
     * @param serviceId unique identifier of service
     * @param type service offers
     * @param description service explanation
     * @param cost service value
     * @throws Exception if an error occurs while accessing the database 
     */
    public void update(int serviceId, String type, String description, double cost) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = servdb.get(serviceId);
        
        service.setServiceType(type);
        service.setServiceDesc(description);
        service.setServiceCost(serviceId);
        
        servdb.update(service);
    }
    
    /**
     * Removes service from MMTDB
     * 
     * @param serviceId unique identifier of service
     * @throws Exception if an error occurs while accessing the database
     */
    public void delete(int serviceId) throws Exception {
        ServiceDB servdb = new ServiceDB();
        Service service = servdb.get(serviceId);
        
        servdb.delete(service);
    }
}
