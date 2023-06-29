package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_RESIDENT_HOUSE")
public class ResidentHouseEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "ID_RESIDENT", referencedColumnName = "id")
    private ResidentEntity resident;

    @OneToOne
    @JoinColumn(name = "ID_HOUSE", referencedColumnName = "id")
    private HouseEntity house;

    public ResidentEntity getResident() {
        return resident;
    }

    public void setResident(ResidentEntity resident) {
        this.resident = resident;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }
}
