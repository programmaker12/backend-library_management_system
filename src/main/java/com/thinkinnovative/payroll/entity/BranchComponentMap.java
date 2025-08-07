package com.thinkinnovative.payroll.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "branch_component_map")
public class BranchComponentMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "branch_id", nullable = false)
    private Integer branchId;

    @Column(name = "component_id", nullable = false)
    private Integer componentId;


}

