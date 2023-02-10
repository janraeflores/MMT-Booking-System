/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.AccountDB;
import models.Account;

/**
 *
 * @author joann
 */
public class TestPersist {
    public static void main(String[] args) throws Exception {
        Account acc = new Account("test@gmail.ca", true, "test", "password");
        
        AccountDB accdb = new AccountDB();
        accdb.insert(acc);
    }
}
