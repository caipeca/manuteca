package com.organizacao.manuteca.model.condominium;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MNT_EMPLOYEE")
public class EmployeeEntity extends BaseEntity {

    @Column(name = "NAME",  nullable = false)
    private String name;

    @Column(name = "CONTACT",  nullable = false)
    private String contact;

    @Column(name = "EMAIL",  nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "ID_FUNCTION", referencedColumnName = "id")
    private FunctionEntity function;

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

    public FunctionEntity getFunction() {
        return function;
    }

    public void setFunction(FunctionEntity function) {
        this.function = function;
    }
}
