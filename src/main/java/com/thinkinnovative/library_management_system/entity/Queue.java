package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "queue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_id")
    private Integer queueId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", referencedColumnName = "bookid", nullable = false)
    private LibraryInformation libraryInformation;
}
