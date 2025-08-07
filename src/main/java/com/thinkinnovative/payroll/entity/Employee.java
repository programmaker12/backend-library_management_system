package com.thinkinnovative.payroll.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data                     // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor        // Generates a no-args constructor
@AllArgsConstructor       // Generates an all-args constructor
@Builder                  // Enables builder pattern
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "designation_id")
    private int designationId;
}
