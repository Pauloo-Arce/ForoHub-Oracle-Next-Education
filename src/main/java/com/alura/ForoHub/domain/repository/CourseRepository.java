package com.alura.ForoHub.domain.repository;


import com.alura.ForoHub.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}