package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name="status_table")
public class StatusTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusID;
    @Column(name = "status_name", nullable = false)
    private String statusName;

    public StatusTable(Long statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public StatusTable() {
    }
}
