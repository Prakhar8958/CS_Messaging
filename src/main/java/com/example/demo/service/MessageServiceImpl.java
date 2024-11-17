package com.example.demo.service;

import com.example.demo.Entity.Message;
import com.example.demo.enums.MessageStatus;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public List<Message> getMessagesByStatus(MessageStatus status) {
        return messageRepository.findByStatus(status);
    }

    @Override
    public Message assignMessageToAgent(UUID messageId, UUID agentId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setAssignedAgent(agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found")));
        message.setStatus(MessageStatus.ASSIGNED);
        message.setUpdatedAt(LocalDateTime.now());

        Message updatedMessage = messageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/messages", updatedMessage); // Broadcast the update
        return updatedMessage;
    }

    @Override
    public Message respondToMessage(UUID messageId, String response) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setResponse(response);
        message.setStatus(MessageStatus.RESPONDED);
        message.setUpdatedAt(LocalDateTime.now());

        Message updatedMessage = messageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/messages", updatedMessage); // Broadcast the response
        return updatedMessage;
    }
}
