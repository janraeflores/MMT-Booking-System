/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.EmergencyContactDB;
import java.util.List;
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
    
    public void insert(String ecName, String phone, String email) throws Exception {
        EmergencyContact ec = new EmergencyContact(ecName, phone);
        
        ec.setEcEmail(email);
        
        EmergencyContactDB ecDB = new EmergencyContactDB();
        ecDB.insert(ec);
    }
    
    public void update(String ecName, String phone, String email) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecName);
        
        ec.setEcName(ecName);
        ec.setEcPhone(phone);
        ec.setEcEmail(email);
        
        ecDB.update(ec);
    }
    
    public void delete(String ecName) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecName);
        
        ecDB.delete(ec);
    }
}
