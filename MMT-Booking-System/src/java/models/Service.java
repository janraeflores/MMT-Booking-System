package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
    , @NamedQuery(name = "Service.findByServiceType", query = "SELECT s FROM Service s WHERE s.serviceType = :serviceType")
    , @NamedQuery(name = "Service.findByServiceDesc", query = "SELECT s FROM Service s WHERE s.serviceDesc = :serviceDesc")
    , @NamedQuery(name = "Service.findByServiceCost", query = "SELECT s FROM Service s WHERE s.serviceCost = :serviceCost")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "service_type")
    private String serviceType;
    @Basic(optional = false)
    @Column(name = "service_desc")
    private String serviceDesc;
    @Basic(optional = false)
    @Column(name = "service_cost")
    private double serviceCost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    public Service() {
    }

    public Service(String serviceType) {
        this.serviceType = serviceType;
    }

    public Service(String serviceType, String serviceDesc, double serviceCost) {
        this.serviceType = serviceType;
        this.serviceDesc = serviceDesc;
        this.serviceCost = serviceCost;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
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
        hash += (serviceType != null ? serviceType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceType == null && other.serviceType != null) || (this.serviceType != null && !this.serviceType.equals(other.serviceType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Service[ serviceType=" + serviceType + " ]";
    }
    
}
