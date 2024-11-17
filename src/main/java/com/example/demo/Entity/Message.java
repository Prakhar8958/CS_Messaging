package com.example.demo.Entity;

import com.example.demo.enums.MessageStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String customerName;
    private String email;
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne
    private Agent assignedAgent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
