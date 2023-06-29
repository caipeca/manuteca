package com.organizacao.manuteca.dto;

import javax.persistence.Column;

public class FunctionDTO {

    private long id;

    private String idFunction;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
