package com.alura.ForoHub.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.ForoHub.domain.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{}