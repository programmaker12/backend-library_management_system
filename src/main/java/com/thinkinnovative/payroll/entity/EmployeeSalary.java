package com.thinkinnovative.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee_salary")
@Data
public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_id", nullable = false)
    private Integer empId;

    @Column(name = "total_working_days")
    private Integer totalWorkingDays;

    @Column(name = "number_of_working_days_attended")
    private Integer numberOfWorkingDaysAttended;

    @Column(name = "paid_leaves")
    private Integer paidLeaves;

    @Column(name = "unpaid_leaves")
    private Integer unpaidLeaves;

    @Column(name = "balance_leaves")
    private Integer balanceLeaves;

    @Column(name = "gross_salary", precision = 10, scale = 2)
    private BigDecimal grossSalary;

    @Column(name = "deductions", precision = 10, scale = 2)
    private BigDecimal deductions;

    @Column(name = "net_value", precision = 10, scale = 2)
    private BigDecimal netValue;

    @Column(name = "date")
    private LocalDate date;

    // Getters and Setters
}
