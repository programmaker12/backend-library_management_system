package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

@Builder
@AllArgsConstructor
@Table(name = "widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer widget_id;

    @Column(name = "widget_name", nullable = true)
    private String widget_name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updated_at;

    @Column(name = "is_active", nullable = true)
    private Integer is_active;

    @Column(name = "icon", nullable = true)
    private String icon;

    @Column(name = "backgroundColor", nullable = true)
    private String backgroundColor;

    @Column(name = "onClickPath", nullable = true)
    private String onClickPath;

    @Column(name = "isCommon", nullable = true)
    private String isCommon;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "color", nullable = true)
    private String color;


}
