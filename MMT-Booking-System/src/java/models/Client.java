/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Keith
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByClientId", query = "SELECT c FROM Client c WHERE c.clientId = :clientId")
    , @NamedQuery(name = "Client.findByFullName", query = "SELECT c FROM Client c WHERE c.fullName = :fullName")
    , @NamedQuery(name = "Client.findByContactEmail", query = "SELECT c FROM Client c WHERE c.contactEmail = :contactEmail")
    , @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone")
    , @NamedQuery(name = "Client.findByBirthdate", query = "SELECT c FROM Client c WHERE c.birthdate = :birthdate")
    , @NamedQuery(name = "Client.findByAddress", query = "SELECT c FROM Client c WHERE c.address = :address")
    , @NamedQuery(name = "Client.findByMedicalInfo", query = "SELECT c FROM Client c WHERE c.medicalInfo = :medicalInfo")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "client_id")
    private Integer clientId;
    @Basic(optional = false)
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = false)
    @Column(name = "contact_email")
    private String contactEmail;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Column(name = "medical_info")
    private String medicalInfo;
    @JoinColumn(name = "ec_contact", referencedColumnName = "ec_name")
    @ManyToOne(fetch = FetchType.EAGER)
    private EmergencyContact ecContact;
    @JoinColumn(name = "account_username", referencedColumnName = "username")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account accountUsername;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    public Client() {
    }

    public Client(Integer clientId) {
        this.clientId = clientId;
    }

    public Client(Integer clientId, String fullName, String contactEmail, String phone, String address) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.address = address;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public EmergencyContact getEcContact() {
        return ecContact;
    }

    public void setEcContact(EmergencyContact ecContact) {
        this.ecContact = ecContact;
    }

    public Account getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(Account accountUsername) {
        this.accountUsername = accountUsername;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Client[ clientId=" + clientId + " ]";
    }
    
}
