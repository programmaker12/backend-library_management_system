package com.thinkinnovative.library_management_system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QADTO {

    private String question;
    private String answer;
    private Integer topicId;
}
