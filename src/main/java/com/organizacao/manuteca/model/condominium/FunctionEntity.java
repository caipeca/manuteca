package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_FUNCTION")
public class FunctionEntity extends BaseEntity {
    @Column(name = "ID_FUNCTION",  nullable = false)
    private String idFunction;
    @Column(name = "DESCRIPTION",  nullable = false)
    private String description;

    public String getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(String idFunction) {
        this.idFunction = idFunction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
