package com.thinkinnovative.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeComponentAmountDTO {
    private Integer id;
    private Integer employeeId;
    private Integer componentId;
    private BigDecimal amount;
    private LocalDate date;
}
