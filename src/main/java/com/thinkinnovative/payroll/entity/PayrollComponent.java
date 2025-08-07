package com.thinkinnovative.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payroll_components")
@Data
public class PayrollComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "component_type", length = 50, nullable = false)
    private String componentType;

    @Column(name = "component_category", length = 50, nullable = false)
    private String componentCategory;

    @Column(name = "component_name", length = 100, nullable = false)
    private String componentName;
}
