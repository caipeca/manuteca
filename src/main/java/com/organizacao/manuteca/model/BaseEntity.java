package com.organizacao.manuteca.model;

import javax.persistence.*;
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public Long getId() {
        return id;
    }

    /*
     *
     *   This method is mean for testing purposes only (create mock data), as we should not set Ids manually,
     *   Hibernate will do it automatically
     *
     **/
    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }
}
