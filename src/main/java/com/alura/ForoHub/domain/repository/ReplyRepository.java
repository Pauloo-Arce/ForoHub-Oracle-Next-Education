package com.alura.ForoHub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.ForoHub.domain.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{}