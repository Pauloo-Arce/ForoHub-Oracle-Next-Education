package com.alura.ForoHub.dto.topic;

import java.time.LocalDateTime;

import com.alura.ForoHub.domain.model.Topic;
import com.alura.ForoHub.domain.model.TopicStatus;

public record ListTopicDTO(Long topicId,
                           String title,
                           LocalDateTime createdAt,
                           TopicStatus status,
                           String authorName,
                           String courseName
) {
        public ListTopicDTO(Topic topic) {
            this(topic.getTopicId(), topic.getTitle(), topic.getCreatedAt(), topic.getStatus(),
                    topic.getUser().getName(), topic.getCourse().getName());
        }
}