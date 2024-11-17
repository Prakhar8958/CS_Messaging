package com.example.demo.service;

import com.example.demo.Entity.Message;
import com.example.demo.enums.MessageStatus;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    List<Message> getMessagesByStatus(MessageStatus status);
    Message assignMessageToAgent(UUID messageId, UUID agentId);
    Message respondToMessage(UUID messageId, String response);
}
