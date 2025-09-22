package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better than AUTO for most RDBMS
    @Column(name = "bookid")
    private Integer bookID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "published_year")
    private Integer publishedYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "statusID")
    private StatusTable status;

    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer stock = 0;

    @OneToMany(
            mappedBy = "libraryInformation",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Queue> inqueue = new ArrayList<>();

    /**
     * Add a queue item safely.
     */
    public void addToQueue(Queue queueEntry) {
        inqueue.add(queueEntry);
        queueEntry.setLibraryInformation(this);
    }

    /**
     * Remove a queue item safely.
     */
    public void removeFromQueue(Queue queueEntry) {
        inqueue.remove(queueEntry);
        queueEntry.setLibraryInformation(null);
    }
}
