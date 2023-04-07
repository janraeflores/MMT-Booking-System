package services;

import dataaccess.AccountDB;
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
    
    public void update(String fullName, String email, boolean active, String username, String password, String phone, Role role, String address, EmergencyContact ec) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        account.setFullName(fullName);
        account.setEmail(email);
        account.setActive(active);
        account.setPassword(password);
        account.setPhone(phone);
        account.setRole(role);
        account.setAddress(address);
        account.setEcContact(ec);
        
        accountDB.update(account);
    }
    
    public void delete(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        accountDB.delete(account);
    }
}
