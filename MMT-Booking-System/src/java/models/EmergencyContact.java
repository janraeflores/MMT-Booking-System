package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joanna
 */
@Entity
@Table(name = "emergency_contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmergencyContact.findAll", query = "SELECT e FROM EmergencyContact e")
    , @NamedQuery(name = "EmergencyContact.findByEcId", query = "SELECT e FROM EmergencyContact e WHERE e.ecId = :ecId")
    , @NamedQuery(name = "EmergencyContact.findByEcName", query = "SELECT e FROM EmergencyContact e WHERE e.ecName = :ecName")
    , @NamedQuery(name = "EmergencyContact.findByEcPhone", query = "SELECT e FROM EmergencyContact e WHERE e.ecPhone = :ecPhone")
    , @NamedQuery(name = "EmergencyContact.findByEcEmail", query = "SELECT e FROM EmergencyContact e WHERE e.ecEmail = :ecEmail")
    , @NamedQuery(name = "EmergencyContact.findByEcRelation", query = "SELECT e FROM EmergencyContact e WHERE e.ecRelation = :ecRelation")})
public class EmergencyContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ec_id")
    private Integer ecId;
    @Column(name = "ec_name")
    private String ecName;
    @Column(name = "ec_phone")
    private String ecPhone;
    @Column(name = "ec_email")
    private String ecEmail;
    @Column(name = "ec_relation")
    private String ecRelation;
    @JoinColumn(name = "fk_account", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account fkAccount;

    public EmergencyContact() {
    }

    public EmergencyContact(Integer ecId) {
        this.ecId = ecId;
    }

    public Integer getEcId() {
        return ecId;
    }

    public void setEcId(Integer ecId) {
        this.ecId = ecId;
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

    public String getEcRelation() {
        return ecRelation;
    }

    public void setEcRelation(String ecRelation) {
        this.ecRelation = ecRelation;
    }

    public Account getFkAccount() {
        return fkAccount;
    }

    public void setFkAccount(Account fkAccount) {
        this.fkAccount = fkAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecId != null ? ecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmergencyContact)) {
            return false;
        }
        EmergencyContact other = (EmergencyContact) object;
        if ((this.ecId == null && other.ecId != null) || (this.ecId != null && !this.ecId.equals(other.ecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.EmergencyContact[ ecId=" + ecId + " ]";
    }
    
}
