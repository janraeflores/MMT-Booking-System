package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author Keith
 */
public class RoleService {
    
    /**
     * Gets role based on specific unique identifier
     * 
     * @param roleId unique identifier for role
     * @return specific role based on identifier
     * @throws Exception if an error occurs while accessing the database
     */
    public Role get(int roleId) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleId);
        
        return role;
    }
    
    /**
     * Get a list of all the roles in the MMTDB
     * 
     * @return list of all roles
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        
        return roles;
    }
}
