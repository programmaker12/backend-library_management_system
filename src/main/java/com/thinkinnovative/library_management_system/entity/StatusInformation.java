package com.thinkinnovative.library_management_system.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StatusInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long StatusId;

    private String statusname;

    public StatusInformation() {
    }

    public StatusInformation( String statusName) {
        //StatusId = statusId;
        this.statusname = statusName;
    }
    //Getter Setter
    public String getStatusName() {
        return statusname;
    }

    public void setStatusName(String statusName) {
        this.statusname = statusName;
    }

    public Long getStatusId() {
        return StatusId;
    }

    public void setStatusId(Long statusId) {
        StatusId = statusId;
    }

}
