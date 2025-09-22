package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.QuestionAnswerDTO;
import com.thinkinnovative.library_management_system.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {
    //List<QuestionAnswer> findByTopicId(Long topicId);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.QuestionAnswerDTO(" +
            "q.question, q.answer)"+" FROM QuestionAnswer q"+" WHERE topic.id = :topicId")
    List<QuestionAnswerDTO> findByTopic(@RequestParam("topicId") Integer topicId);
}
