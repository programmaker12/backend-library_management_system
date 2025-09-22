package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalTime;
import java.time.Duration;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String libraryName;

    @Column(nullable = false, unique = true)
    private String libraryCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @ManyToOne // Assuming a library can belong to one branch.
    @JoinColumn(name = "branch_id", nullable = false)
    private LibraryBranch branch;

    @Column(nullable = false)
    private Integer totalBranches;

    /**
     * Calculates and updates the number of open hours based on `openingTime` and `closingTime`.
     */
    @PrePersist
    @PreUpdate
    public void calculateHours() {
        if (openingTime != null && closingTime != null) {
            this.hours = (int) Duration.between(openingTime, closingTime).toHours();
        }
    }
}
