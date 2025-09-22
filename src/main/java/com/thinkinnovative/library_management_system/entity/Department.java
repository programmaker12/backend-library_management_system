package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "book_number")
    private Integer bookNumbers;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private LibraryBranch branch;
}
