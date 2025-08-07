package com.thinkinnovative.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payroll_scheduler_log")
@Data
public class PayrollSchedulerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "log_date")
    private LocalDateTime logDate = LocalDateTime.now();
}
