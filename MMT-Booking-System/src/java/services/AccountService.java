package services;

import dataaccess.AccountDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Account;
import models.Role;


public class AccountService {
    
    /**
     * This method takes in the username and password of an account 
     * and returns an Account object if there is a match found of the credentials in the database. 
     * This method should be used when verifying an account during login.
     * 
     * @param username of the account
     * @param password of the account
     * @return an Account, if there is a match of the username 
     *         and password in the database, otherwise, returns null if there is no match
     */
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
    
    /**
     * This method deactivates an account by taking in the username as a parameter. It will obtain 
     * an Account object given the username provided and set the attribute Active to false.
     * This change will update the affected account in the database to reflect these changes made.
     * 
     * @param username of the account to be deactivated
     * @throws Exception if the username does not exist in the database, indicating that
     *         the account does not exist
     */
    public void deactivate(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        
        try {
            Account account = accountDB.get(username);
            account.setActive(false);
            
            accountDB.update(account);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    
    /**
     * Gets the user account as an Account object.
     * 
     * @param username of the account
     * @return an Account associated with the username, returns null if there is no match
     * @throws Exception if there is no account associated with the username
     */
    public Account get(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        return account;
    }
    
    /**
     * Gets a list of all accounts in the database as a List of Account type
     * 
     * @return a list of all accounts
     * @throws Exception if the list is null.
     */
    public List<Account> getAll() throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        return accounts;
    }
    
    /**
     * Gets a list of all active accounts; accounts with their Active status as 'true', in 
     * the database
     * 
     * @return a list of active accounts
     * @throws Exception if there is an error while accessing the database
     */
    public List<Account> getAllActive() throws Exception {
        List<Account> allAccounts = getAll();
        List<Account> activeAccounts = new ArrayList<>();
        
        for (Account account : allAccounts) {
            
            if (account.getActive()) {
                activeAccounts.add(account);
            }
        }
        return activeAccounts;
    }
                  
    /**
     * Adds the new valid user account. This method creates an Account object with the
     * provided credentials. By default, an account with the role of a Client is added with
     * client privileges and its Active status is set to true indicating that the account is active.
     * 
     * @param username provided by the user to the account
     * @param fullName of the user
     * @param email provided by the user to the account
     * @param password provided by the user to the account
     * @param phone number of the user
     * @param address of the user
     * @throws Exception if any parameters are null
     */
    public void insert(String username, String fullName, String email, 
            String password, String phone, String address) throws Exception {
        
        RoleService rs = new RoleService();
        Role clientRole = rs.get(2);
        
        Account account = new Account(username, fullName, email, true, password, phone, address);
        account.setRole(clientRole);
        
        AccountDB accountDB = new AccountDB();
        accountDB.insert(account);
    }
    
    /**
     * Updates the user's account, taking in all the attributes of the Account as a parameter.
     * 
     * @param fullName of the user
     * @param email provided by the user to the account
     * @param active status of the account
     * @param username provided by the user to the account
     * @param password provided by the user to the account
     * @param phone number of the user
     * @param role of the user in the system
     * @param birthdate of the user
     * @param address of the user
     * @throws Exception if the username does not exist
     */
    public void update(String fullName, String email, boolean active, String username, String password, 
            String phone, Role role, Date birthdate, String address) throws Exception {
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
     * Updates the user's account, excluding their Role, Active status, Birthday, and Password.
     * This method is used when updating the user's information from users with the Admin role.
     * 
     * @param username provided by the user to the account
     * @param fullName of the user
     * @param email provided by the user to the account
     * @param phone number of the user
     * @param address of the user
     * @throws Exception if the username does not exist
     */
    public void update(String username, String fullName, String email, String phone, String address) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        account.setEmail(email);
        account.setFullName(fullName);
        account.setPhone(phone);
        account.setAddress(address);
        
        accountDB.update(account);
    }
    
    /**
     * Updates the user's account, excluding their Role, Active status, and Birthdate.
     * This method is used if the user has not specified their birthdate.
     * 
     * @param username provided by the user to the account
     * @param fullName of the user
     * @param email provided by the user to the account
     * @param password provided by the user to the account
     * @param phone number of the user
     * @param address of the user
     * @throws Exception if the username does not exist
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
    
    /**
     * This method deletes an account, given the provided username, in the database.
     * 
     * @param username of the account to be deleted
     * @throws Exception if there is an error while accessing the database
     */
    public void delete(String username) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(username);
        
        accountDB.delete(account);
    }
}
