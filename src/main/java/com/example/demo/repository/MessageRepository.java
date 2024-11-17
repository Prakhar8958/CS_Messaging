package com.example.demo.repository;

import com.example.demo.Entity.Message;
import com.example.demo.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByStatus(MessageStatus status);
    List<Message> findByAssignedAgentId(UUID agentId);
}
