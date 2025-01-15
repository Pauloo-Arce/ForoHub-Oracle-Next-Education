package com.alura.ForoHub.domain.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.alura.ForoHub.domain.model.Category;
import com.alura.ForoHub.domain.model.Course;
import com.alura.ForoHub.domain.model.ProfileType;
import com.alura.ForoHub.domain.model.Topic;
import com.alura.ForoHub.domain.model.User;
import com.alura.ForoHub.dto.user.RegisterUserDTO;
import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("should fail if user or couse doesn't exist")
    void testTopicValidToSave(){
        
        var userDto = new RegisterUserDTO("guido", "guido.rodriguez@gmail.com","62738" , ProfileType.STUDENT);
        var user = new User(userDto);
        em.persist(user);

        var course = new Course(null, "Programacion Java", Category.BACK_END);
        em.persist(course);

        Topic validTopic = new Topic();
        validTopic.setTitle("Título válido");
        validTopic.setMessage("Mensaje válido");
        validTopic.setUser(user);
        validTopic.setCourse(course);

        Topic invalidTopic = new Topic();
        invalidTopic.setTitle("Título inválido");
        invalidTopic.setMessage("Mensaje inválido");
        invalidTopic.setUser(null);
        invalidTopic.setCourse(course);

        Topic validResult = topicRepository.save(validTopic);
        assertNotNull(validResult.getTopicId());

        assertThrows(DataIntegrityViolationException.class, () -> {
            topicRepository.save(invalidTopic);
        });
    }    
}