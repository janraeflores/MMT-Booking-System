/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ClientDB;
import java.util.List;
import models.Client;

/**
 *
 * @author Keith
 */
public class ClientService {
    
    public Client get(int clientId) throws Exception {
        ClientDB clientDB = new ClientDB();
        Client client = clientDB.get(clientId);
        return client;
    }
    
    public List<Client> getAll() throws Exception {
        ClientDB clientDB = new ClientDB();
        List<Client> clients = clientDB.getAll();
        return clients;
    }
    
    public Client get(String username) throws Exception {
        ClientDB clientDB = new ClientDB();
        Client client = clientDB.get(username);
        return client;
    }
    
    public void insert(int clientId, String fullName, String contactEmail, String phone, String address) throws Exception {
        Client client = new Client(0, fullName, contactEmail, phone, address);
        
        ClientDB clientDB = new ClientDB();
        clientDB.insert(client);
    }
    
    public void update(int clientId, String fullName, String contactEmail, String phone, String address) throws Exception {
        ClientDB clientDB = new ClientDB();
        Client client = clientDB.get(clientId);
        client.setFullName(fullName);
        client.setContactEmail(contactEmail);
        client.setPhone(phone);
        client.setAddress(address);
        
        clientDB.update(client);
    }
    
    public void delete(int clientId) throws Exception {
        ClientDB clientDB = new ClientDB();
        Client client = clientDB.get(clientId);
        
        clientDB.delete(client);
    }    
}
