package com.thinkinnovative.library_management_system.dto;

import java.time.LocalDate;

public class MemberListDTO {

    private Integer memberID;
    private String memberName;
    private LocalDate membershipDate;

    public MemberListDTO(Integer memberID, String memberName, LocalDate membershipDate) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.membershipDate = membershipDate;
    }

    // Getters and Setters

    public Integer getMemberID() {
        return memberID;
    }

    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}
