package services;

import dataaccess.AccountDB;
import dataaccess.EmergencyContactDB;
import java.util.List;
import models.Account;
import models.EmergencyContact;

/**
 *
 * @author Keith
 */
public class EmergencyContactService {
    
    /**
     * Retrieves EmergencyContact based on ID
     * 
     * @param ecId unique identifier of emergency contact
     * @return specific emergency contact based on ID
     * @throws Exception if an error occurs while accessing the database
     */
    public EmergencyContact get(int ecId) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        
        EmergencyContact ec = ecDB.get(ecId);
        return ec;
    }
    
    /**
     * Gets all EmergencyContacts in the MMTDB datadase
     * 
     * @return list of all emergency contacts
     * @throws Exception if an error occurs while accessing the database
     */
    public List<EmergencyContact> getAll() throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        List<EmergencyContact> ecs = ecDB.getAll();
        
        return ecs;
    }
    
    /**
     * Retrieves all Emergency Contacts based on specific username
     * 
     * @param username of patient account
     * @return emergency contacts specific to patient account
     * @throws Exception if an error occurs while accessing the database
     */
    public List<EmergencyContact> getAll(String username) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        List<EmergencyContact> ecs = ecDB.getAll(username);
        
        return ecs;
    }
    
    /**
     * Inserts new emergency contact
     * 
     * @param ecName emergency contact name
     * @param ecPhone emergency contact phone number
     * @param ecEmail emergency contact email address
     * @param ecRelation emergency contacts relationship to patient
     * @param username emergency contact username
     * @throws Exception  if an error occurs while accessing the database
     */
    public void insert(String ecName, String ecPhone, String ecEmail, String ecRelation, String username) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        AccountDB accountDB = new AccountDB();
        
        Account account = accountDB.get(username);
        EmergencyContact ec = new EmergencyContact(0);
        
        ec.setEcEmail(ecEmail);
        ec.setEcName(ecName);
        ec.setEcPhone(ecPhone);
        ec.setEcRelation(ecRelation);
        ec.setFkAccount(account);

        ecDB.insert(ec);
    }
    
    /**
     * Update EmergencyContact info
     * 
     * @param ecId emergency contact unique ID
     * @param ecName emergency contact name
     * @param ecRelation emergency contact relationship with patient
     * @param ecPhone emergency contact phone number
     * @param ecEmail emergency contact email address
     * @throws Exception if an error occurs while accessing the database
     */
    public void update(int ecId, String ecName, String ecRelation, String ecPhone, String ecEmail) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecId);
        
        ec.setEcEmail(ecEmail);
        ec.setEcName(ecName);
        ec.setEcPhone(ecPhone);
        ec.setEcRelation(ecRelation);
        
        ecDB.update(ec);
    }
  
   /**
    * Deletes specific EmergencyContact from database
    * 
    * @param ecId unique identifier of emergency contact
    * @throws Exception if an error occurs while accessing the database
    */
    public void delete(int ecId) throws Exception {
        EmergencyContactDB ecDB = new EmergencyContactDB();
        EmergencyContact ec = ecDB.get(ecId);
        
        ecDB.delete(ec);
    }
}
