
package services;

import dataaccess.EmergencyContactDB;
import java.util.List;
import models.EmergencyContact;

/**
 *
 * @author Keith
 */
public class EmergencyContactService {
    

    public EmergencyContact get(int ecId) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        
        EmergencyContact ec = ecDB.get(ecId);
        return ec;
    }
    
    public List<EmergencyContact> getAll() throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        List<EmergencyContact> ecs = ecDB.getAll();
        
        return ecs;
    }
    public List<EmergencyContact> getAll(String username) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        List<EmergencyContact> ecs = ecDB.getAll(username);
        
        return ecs;
    }
    
    public void insert(String ecName, String ecPhone, String ecEmail, String ecRelation) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = new EmergencyContact(0);
        
        ec.setEcEmail(ecEmail);
        ec.setEcName(ecName);
        ec.setEcPhone(ecPhone);
        ec.setEcRelation(ecRelation);

        ecDB.insert(ec);
    }
    public void update(int ecId, String ecName, String ecRelation, String ecPhone, String ecEmail) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecId);
        
        ec.setEcEmail(ecEmail);
        ec.setEcName(ecName);
        ec.setEcPhone(ecPhone);
        ec.setEcRelation(ecRelation);
        
        ecDB.update(ec);
    }
  
   
    public void delete(int ecId) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecId);
        
        ecDB.delete(ec);
    }
}
