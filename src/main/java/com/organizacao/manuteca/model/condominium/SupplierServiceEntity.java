package com.organizacao.manuteca.model.condominium;

import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MNT_SUPPLIER_SERVICE")
public class SupplierServiceEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "ID_SUPPLIER", referencedColumnName = "id")
    private SupplierEntity supplier;

    @OneToOne
    @JoinColumn(name = "ID_SERVICE", referencedColumnName = "id")
    private ServiceEntity service;
}
