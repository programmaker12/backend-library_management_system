package com.thinkinnovative.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class EmployeeSalaryDto {

    private Integer empId;
    private Integer totalWorkingDays;
    private Integer numberOfWorkingDaysAttended;
    private Integer paidLeaves;
    private Integer unpaidLeaves;
    private Integer balanceLeaves;
    private BigDecimal grossSalary;
    private BigDecimal deductions;
    private BigDecimal netValue;
    private LocalDate date;

//    // Constructors
//
//    public EmployeeSalaryDto() {}
//
//    public EmployeeSalaryDto(Integer empId, Integer totalWorkingDays,
//                             Integer numberOfWorkingDaysAttended, Integer paidLeaves,
//                             Integer unpaidLeaves, Integer balanceLeaves,
//                             BigDecimal grossSalary, BigDecimal deductions,
//                             BigDecimal netValue, LocalDate date) {
//        this.empId = empId;
//        this.totalWorkingDays = totalWorkingDays;
//        this.numberOfWorkingDaysAttended = numberOfWorkingDaysAttended;
//        this.paidLeaves = paidLeaves;
//        this.unpaidLeaves = unpaidLeaves;
//        this.balanceLeaves = balanceLeaves;
//        this.grossSalary = grossSalary;
//        this.deductions = deductions;
//        this.netValue = netValue;
//        this.date = date;
//    }

    // Getters and Setters
}
