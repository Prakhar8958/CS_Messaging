package com.example.demo.controller;

import com.example.demo.Entity.Message;
import com.example.demo.enums.MessageStatus;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getMessages(@RequestParam MessageStatus status) {
        return messageService.getMessagesByStatus(status);
    }

    @PutMapping("/{messageId}/assign")
    public Message assignMessage(@PathVariable UUID messageId, @RequestBody Map<String, UUID> payload) {
        return messageService.assignMessageToAgent(messageId, payload.get("agentId"));
    }

    @PostMapping("/{messageId}/respond")
    public Message respondToMessage(@PathVariable UUID messageId, @RequestBody Map<String, String> payload) {
        return messageService.respondToMessage(messageId, payload.get("response"));
    }
}
