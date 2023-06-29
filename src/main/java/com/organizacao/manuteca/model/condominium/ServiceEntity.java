package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_SERVICE")
public class ServiceEntity extends BaseEntity{

    @Column(name = "ID_SERVICE",  nullable = false)
    private String idService;

    @Column(name = "DESCRIPTION",  nullable = false)
    private String description;

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
