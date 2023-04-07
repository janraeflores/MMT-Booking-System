package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Keith
 */
@Entity
@Table(name = "emergency_contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmergencyContact.findAll", query = "SELECT e FROM EmergencyContact e")
    , @NamedQuery(name = "EmergencyContact.findByEcName", query = "SELECT e FROM EmergencyContact e WHERE e.ecName = :ecName")
    , @NamedQuery(name = "EmergencyContact.findByEcPhone", query = "SELECT e FROM EmergencyContact e WHERE e.ecPhone = :ecPhone")
    , @NamedQuery(name = "EmergencyContact.findByEcEmail", query = "SELECT e FROM EmergencyContact e WHERE e.ecEmail = :ecEmail")})

public class EmergencyContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ec_name")
    private String ecName;
    @Basic(optional = false)
    @Column(name = "ec_phone")
    private String ecPhone;
    @Column(name = "ec_email")
    private String ecEmail;
    @OneToMany(mappedBy = "ecContact", fetch = FetchType.EAGER)
    private List<Account> accountList;
     @JoinColumn(name = "account_username", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountUsername;

    public EmergencyContact() {
    }

    public EmergencyContact(String ecName) {
        this.ecName = ecName;
    }

    public EmergencyContact(String ecName, String ecPhone) {
        this.ecName = ecName;
        this.ecPhone = ecPhone;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getEcPhone() {
        return ecPhone;
    }

    public void setEcPhone(String ecPhone) {
        this.ecPhone = ecPhone;
    }

    public String getEcEmail() {
        return ecEmail;
    }

    public void setEcEmail(String ecEmail) {
        this.ecEmail = ecEmail;
    }
    
    public Account getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(Account accountUsername) {
        this.accountUsername = accountUsername;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecName != null ? ecName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmergencyContact)) {
            return false;
        }
        EmergencyContact other = (EmergencyContact) object;
        if ((this.ecName == null && other.ecName != null) || (this.ecName != null && !this.ecName.equals(other.ecName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.EmergencyContact[ ecName=" + ecName + " ]";
    }

}
