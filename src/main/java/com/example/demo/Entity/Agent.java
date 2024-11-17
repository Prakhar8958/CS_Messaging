package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Entity
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
}
