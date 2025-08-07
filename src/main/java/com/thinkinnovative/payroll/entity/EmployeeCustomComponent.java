package com.thinkinnovative.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_custom_components")
@Data
public class EmployeeCustomComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_id", nullable = false)
    private Integer empId;

    @Column(name = "custom_component_id", nullable = false)
    private Integer customComponentId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 50, nullable = false)
    private String type; // credit / debit

    @Column(name = "request_status", length = 50)
    private String requestStatus;

    @Column(name = "paid_status", length = 50)
    private String paidStatus;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    // Getters and Setters
}
