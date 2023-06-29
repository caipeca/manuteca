package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "MNT_HOUSE")
public class HouseEntity extends BaseEntity {

    @Column(name = "ID_HOUSE",  nullable = false)
    private Long idHouse;

    @Column(name = "TOPOLOGY",  nullable = false)
    private String typology;

    @Column(name = "NUMBER",  nullable = false)
    private Long number;

    @OneToOne
    @JoinColumn(name = "ID_STREET", referencedColumnName = "id")
    private StreetEntity street;

    public Long getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(Long idHouse) {
        this.idHouse = idHouse;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public StreetEntity getStreet() {
        return street;
    }

    public void setStreet(StreetEntity street) {
        this.street = street;
    }
}
