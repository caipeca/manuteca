package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "MNT_RESIDENT")
public class ResidentEntity extends BaseEntity {

    @Column(name = "BI",  nullable = false)
    private String bi;

    @Column(name = "NAME",  nullable = false)
    private String name;

    @Column(name = "EMAIL",  nullable = false)
    private String email;

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
