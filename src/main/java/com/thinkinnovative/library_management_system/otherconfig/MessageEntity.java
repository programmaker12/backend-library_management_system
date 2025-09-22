package com.thinkinnovative.library_management_system.otherconfig;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageEntity {

    private String content;

    private String sender;

    private StatusType type;
}
