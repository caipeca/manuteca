package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MNT_SERVICE_SOLICITATION")
public class ServiceSolicitationEntity extends BaseEntity {

    @Column(name = "DESCRIPTION",  nullable = false)
    private String description;

    @Column(name = "SOLICITATION_DATE",  nullable = false)
    private Date solicitationDate;

    @OneToOne
    @JoinColumn(name = "ID_RESIDENT_HOUSE", referencedColumnName = "id")
    private ResidentHouseEntity residentHouse;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSolicitationDate() {
        return solicitationDate;
    }

    public void setSolicitationDate(Date solicitationDate) {
        this.solicitationDate = solicitationDate;
    }

    public ResidentHouseEntity getResidentHouse() {
        return residentHouse;
    }

    public void setResidentHouse(ResidentHouseEntity residentHouse) {
        this.residentHouse = residentHouse;
    }
}
