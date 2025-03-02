package com.alura.ForoHub.service.topic.validations.posting;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.alura.ForoHub.dto.topic.RegisterTopicDTO;
import com.alura.ForoHub.infrastructure.errors.exception.BusinessRulesValidationsException;
import com.alura.ForoHub.service.topic.validations.ValidatorPostingTopic;

@Component
public class CorrectScheduleValidation implements ValidatorPostingTopic{

    @Override
    public void validate(RegisterTopicDTO data) {
        int currentHour = LocalDateTime.now().getHour();

        if (currentHour < 7 || currentHour > 22) {
            throw new BusinessRulesValidationsException("No esta permitido publicar un topico en este horario");
        }
        
    }
    
}
