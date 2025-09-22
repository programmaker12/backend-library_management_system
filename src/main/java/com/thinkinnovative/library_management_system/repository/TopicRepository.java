package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.SubjectTopicDTO;
import com.thinkinnovative.library_management_system.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findBySubjectId(Integer subjectId);
    @Query("SELECT new com.thinkinnovative.library_management_system.dto.SubjectTopicDTO(" +
            "t.subject.subjectName, t.topicName)"+" FROM Topic t")
    List<SubjectTopicDTO> findAllSubjectTopic();
}
