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
@Table(name = "questionAnswer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

//    public QuestionAnswer() {}
//
//    public QuestionAnswer(String question, String answer, Topic topic) {
//        this.question = question;
//        this.answer = answer;
//        this.topic = topic;
//    }

}
