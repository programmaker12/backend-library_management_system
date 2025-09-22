package com.example.payroll.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeCustomComponentDto {

    private Integer empId;
    private Integer customComponentId;
    private BigDecimal amount;
    private String type;
    private String requestStatus;
    private String paidStatus;
    private LocalDateTime createdTime;

//    // Constructors
//    public EmployeeCustomComponentDto() {}
//
//    public EmployeeCustomComponentDto(Integer empId, Integer customComponentId, BigDecimal amount,
//                                      String type, String requestStatus, String paidStatus,
//                                      LocalDateTime createdTime) {
//        this.empId = empId;
//        this.customComponentId = customComponentId;
//        this.amount = amount;
//        this.type = type;
//        this.requestStatus = requestStatus;
//        this.paidStatus = paidStatus;
//        this.createdTime = createdTime;
//    }

    // Getters and Setters
}
