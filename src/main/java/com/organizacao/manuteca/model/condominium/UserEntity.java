package com.organizacao.manuteca.model.condominium;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizacao.manuteca.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MNT_USER")
public class UserEntity extends BaseEntity implements Serializable {

    @Column(name = "USERNAME",  nullable = false)
    private String username;

    @Column(name = "PASSWORD",  nullable = false)
    private String password;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @Column(name = "LOCKED")
    private boolean isNotLocked;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "AUTHORITIES")
    private String[] authorities;

    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    @Column(name = "LAST_LOGIN_DATE_DISPLAY")
    private Date lastLoginDateDisplay;


    @OneToOne
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "id")
    private EmployeeEntity employee;

    @OneToOne
    @JoinColumn(name = "ID_RESIDENT", referencedColumnName = "id")
    private ResidentEntity resident;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ResidentEntity getResident() {
        return resident;
    }

    public void setResident(ResidentEntity resident) {
        this.resident = resident;
    }
}
