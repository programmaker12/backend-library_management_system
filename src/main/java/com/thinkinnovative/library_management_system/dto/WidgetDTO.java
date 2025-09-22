package com.thinkinnovative.library_management_system.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class WidgetDTO {

    private String widget_name;

    private String description;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Integer is_active;

    private String icon;

    private String backgroundColor;

//    private String onClickPath;
//
//    private String isCommon;

    private String type;

    private String color;


}
