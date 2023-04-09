package services;

import dataaccess.AccountDB;
import java.util.Date;
import java.util.List;
import models.Account;
import models.EmergencyContact;
import models.Role;

public class AccountService {
        public Account login(String username, String password) {
        AccountDB accountDB = new AccountDB();
        
        try {
            Account account = accountDB.get(username);
            
            if (password.equals(account.getPassword())) {
                return account;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
    public Account get(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        return account;
    }
    
    public List<Account> getAll() throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        return accounts;
    }
                      
    public void insert(String username, String fullName, String email, boolean active, String password, String phone, Role role, String address) throws Exception {
        Account account = new Account(username, fullName, email, active, password, phone, address);
        account.setRole(role);
        
        AccountDB accountDB = new AccountDB();
        accountDB.insert(account);
    }
    
    /**
     * Updates the user's account by setting all parameters to the Account object
     * 
     * @param fullName
     * @param email
     * @param active
     * @param username
     * @param password
     * @param phone
     * @param role
     * @param birthdate
     * @param address
     * @throws Exception if the username does not exist
     */
    public void update(String fullName, String email, boolean active, String username, String password, String phone, Role role, Date birthdate, String address) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        account.setFullName(fullName);
        account.setEmail(email);
        account.setActive(active);
        account.setPassword(password);
        account.setPhone(phone);
        account.setRole(role);
        account.setAddress(address);
        account.setBirthdate(birthdate);
        
        accountDB.update(account);
    }
    
    /**
     * Updates  the user's account excluding their role and active status
     * 
     * @param username
     * @param fullName
     * @param email
     * @param phone
     * @param birthdate
     * @param address
     * @throws Exception if the username does not exist
     */
    public void update(String username, String fullName, String email, String phone, Date birthdate, String address) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        account.setEmail(email);
        account.setFullName(fullName);
        account.setPhone(phone);
        account.setAddress(address);
        account.setBirthdate(birthdate);
        
        accountDB.update(account);
    }
    
    /**
     * Updates the user's account excluding their birthdate, role, and active status
     * 
     * @param username of the Account
     * @param fullName of the Client
     * @param email address of the Account
     * @param password of the Account
     * @param phone of the Client
     * @param address of the Client
     * @throws Exception if username does not exist
     */
    public void update(String username, String fullName, String email, String password, String phone, String address) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        account.setEmail(email);
        account.setFullName(fullName);
        account.setPhone(phone);
        account.setAddress(address);
        
        accountDB.update(account);
    }
    
    public void delete(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        accountDB.delete(account);
    }
}
