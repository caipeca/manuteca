package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_SUPPLIER")
public class SupplierEntity extends BaseEntity {

    @Column(name = "NIF",  nullable = false)
    private Long nif;

    @Column(name = "NAME",  nullable = false)
    private String name;

    @Column(name = "CONTACT",  nullable = false)
    private String contact;

    @Column(name = "EMAIL",  nullable = false)
    private String email;

    @Column(name = "ADDRESS",  nullable = false)
    private String address;

    public Long getNif() {
        return nif;
    }

    public void setNif(Long nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
