package com.example.demo.config;

import com.example.demo.Entity.Message;
import com.example.demo.enums.MessageStatus;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvDataLoader {

    @Autowired
    private MessageRepository messageRepository;

    @Bean
    public CommandLineRunner loadData(MessageRepository messageRepository) {
        return args -> {
            try {
                Path filePath = Paths.get("src/main/resources/Messages.csv");
                // Read the file and skip the header row
                List<String[]> data = Files.lines(filePath)
                        .skip(1) // Skip the header line
                        .map(line -> line.split(","))
                        .collect(Collectors.toList());

                for (String[] record : data) {
                    Message message = new Message();
                    message.setCustomerName(record[0].trim());
                    message.setEmail(record[1].trim());
                    message.setContent(record[2].trim());
                    message.setStatus(MessageStatus.valueOf(record[3].trim().toUpperCase()));
                    message.setCreatedAt(LocalDateTime.now());
                    message.setUpdatedAt(LocalDateTime.now());

                    messageRepository.save(message);
                }
                System.out.println("CSV data loaded successfully!");
            } catch (Exception e) {
                System.err.println("Error loading data from CSV: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
