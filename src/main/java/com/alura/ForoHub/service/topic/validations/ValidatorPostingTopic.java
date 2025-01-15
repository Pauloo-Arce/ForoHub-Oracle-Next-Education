package com.alura.ForoHub.service.topic.validations;

import com.alura.ForoHub.dto.topic.RegisterTopicDTO;

public interface ValidatorPostingTopic {
    public void validate(RegisterTopicDTO data);
}
