package com.thinkinnovative.library_management_system.controller;


import com.thinkinnovative.library_management_system.dto.*;
import com.thinkinnovative.library_management_system.entity.Subject;
import com.thinkinnovative.library_management_system.entity.Topic;
import com.thinkinnovative.library_management_system.service.serviceImpl.QAServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questionanswer")
@RequiredArgsConstructor
public class QuestionAnswerController {
    private final QAServiceImpl qaService;

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {
        return qaService.getAllSubjects();
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Optional<Subject> subject = qaService.getSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/subjects")
    public String addSubject(@RequestBody SubjectDTO subject) {
        return qaService.addSubject(subject);
    }

    @PostMapping("/topic")
    public String addTopic(@RequestBody TopicDTO topic) {

        return qaService.addTopic(topic);
    }
    @PostMapping("/qa")
    public String addTopic(@RequestBody QADTO qa) {
        return qaService.addQuestionAnswer(qa);
    }

    @GetMapping("/topics/{subjectId}")
    public List<Topic> getTopicsBySubject(@PathVariable Integer subjectId) {
        return qaService.getTopicsBySubject(subjectId);
    }

    @GetMapping("/questions/{topicId}")
    public List<QuestionAnswerDTO> getQuestionsByTopic(@PathVariable Integer topicId) {
        return qaService.getQuestionsByTopic(topicId);
    }

    @GetMapping("/subjecttopic/list")
    public List<SubjectTopicDTO> findAllSubjectTopic() {
        return qaService.findAllSubjectTopic();
    }
}
