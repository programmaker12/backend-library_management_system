package com.thinkinnovative.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class WidgetsListDTO {
    private String widget_name;

    private String description;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Integer is_active;
}
