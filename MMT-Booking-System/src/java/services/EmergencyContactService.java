
package services;

import dataaccess.EmergencyContactDB;
import java.util.List;
import models.Account;
import models.EmergencyContact;

/**
 *
 * @author Keith
 */
public class EmergencyContactService {
    
    public EmergencyContact get(String ecName) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecName);
        
        return ec;
    }
    
    public List<EmergencyContact> getAll() throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        List<EmergencyContact> ecs = ecDB.getAll();
        
        return ecs;
    }
    
    public void insert(Account account, String ecName, String phone, String email, String relation) throws Exception {

        EmergencyContact ec = new EmergencyContact(ecName, phone);
        
        ec.setEcEmail(email);
        ec.setAccountUsername(account);
        ec.setEcRelation(relation);
        
        EmergencyContactDB ecDB = new EmergencyContactDB();
        ecDB.insert(ec);
    }
    
    public void update(Account account, String ecName, String phone, String email, String relation) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecName);
        
        ec.setEcName(ecName);
        ec.setEcPhone(phone);
        ec.setEcEmail(email);
        ec.setAccountUsername(account);
        ec.setEcRelation(relation);
        
        ecDB.update(ec);
    }
    
    public void delete(String ecName) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecName);
        
        ecDB.delete(ec);
    }
}
