package com.thinkinnovative.payroll.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private BigDecimal salary;
    private int departmentId;
    private boolean isActive;
    private int branchId;
}
