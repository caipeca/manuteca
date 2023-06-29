package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_STREET")
public class StreetEntity extends BaseEntity {

    @Column(name = "ID_STREET",  nullable = false)
    private String idStreet;

    @Column(name = "NAME",  nullable = false)
    private String name;

    public String getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(String idStreet) {
        this.idStreet = idStreet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
