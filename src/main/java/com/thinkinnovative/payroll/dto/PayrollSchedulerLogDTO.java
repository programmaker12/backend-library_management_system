package com.thinkinnovative.payroll.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayrollSchedulerLogDTO {
    private Integer id;
    private String message;
    private LocalDateTime logDate;
}
