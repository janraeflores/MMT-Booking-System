package services;

import dataaccess.AccountDB;
import java.util.List;
import models.Account;
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
    
    public void insert(String email, boolean active, String username, String password, Role role) throws Exception {
        Account account = new Account(username, email, active, password);
        account.setRole(role);
        
        AccountDB accountDB = new AccountDB();
        accountDB.insert(account);
    }
    
    public void update(String email, boolean active, String username, String password, Role role) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(email);
        account.setActive(active);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        
        accountDB.update(account);
    }
    
    public void delete(String email) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(email);
        
        accountDB.delete(account);
    }
}
