package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Role;

/**
 *
 * @author Keith
 */
public class RoleDB {

    /**
     * Gets all the role's in the MMTDB database
     * 
     * @return list of roles in the database
     * @throws Exception if an error occurs while accessing the database
     */
    public List<Role> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Role> roleList = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roleList;
        } finally {
            em.close();
        }
    }

    /**
     * Gets specific role based on the unique identifier
     * 
     * @param roleID unique identifier of role
     * @return specific role corresponding with roleID
     * @throws Exception 
     */
    public Role getRole(int roleID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Role role = em.find(Role.class, roleID);
            return role;
        } finally {
            em.close();
        }
    }
}
