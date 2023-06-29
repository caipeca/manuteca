package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MNT_RUNNING_SERVICE")
public class RunningServiceEntity extends BaseEntity {

    @Column(name = "START_DATE",  nullable = false)
    private Date startDate;

    @Column(name = "PREDICTED_END_DATE",  nullable = false)
    private Date predictedEndDate;

    @Column(name = "END_DATE",  nullable = false)
    private Date endDate;

    @OneToOne
    @JoinColumn(name = "ID_SERVICE_SOLICITATION", referencedColumnName = "id")
    private ServiceSolicitationEntity serviceSolicitation;

    @OneToOne
    @JoinColumn(name = "ID_SUPPLIER", referencedColumnName = "id")
    private SupplierEntity supplier;

    @OneToOne
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "id")
    private EmployeeEntity employee;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPredictedEndDate() {
        return predictedEndDate;
    }

    public void setPredictedEndDate(Date predictedEndDate) {
        this.predictedEndDate = predictedEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ServiceSolicitationEntity getServiceSolicitation() {
        return serviceSolicitation;
    }

    public void setServiceSolicitation(ServiceSolicitationEntity serviceSolicitation) {
        this.serviceSolicitation = serviceSolicitation;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
}
