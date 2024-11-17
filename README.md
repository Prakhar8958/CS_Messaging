# Messaging Web Application for Customer Support

A simple and scalable **Java Spring Boot** application designed to manage customer inquiries and agent responses. This project demonstrates the integration of RESTful APIs, a PostgreSQL database, and WebSocket for real-time notifications. It is built to handle a high volume of customer queries while ensuring streamlined agent responses.

---

## Key Features

- **Agent Portal**:  
  Allows multiple agents to log in simultaneously and respond to customer messages.
  
- **Message Management**:
  - View messages based on their status (`NEW`, `ASSIGNED`, `RESPONDED`).
  - Assign messages to agents.
  - Respond to messages.
  
- **Real-Time Notifications**:  
  Notify agents of new messages using WebSocket and STOMP protocol.

- **Data Preloading**:  
  Populate the database from a CSV file (`Messages.csv`).

---

## Technologies Used

- **Java Spring Boot**:
  - RESTful API development.
  - Dependency Injection for service layers.
  
- **WebSocket**:
  - Real-time communication between agents and the system.
  - STOMP protocol for messaging.
  
- **PostgreSQL**:
  - Relational database for storing messages and agent data.
  
- **Apache POI**:
  - Read and process Excel files for data import.
  
- **Lombok**:
  - Simplify boilerplate code (e.g., getters/setters).
  
- **Maven**:
  - Build and dependency management.

---

## Project Structure

```plaintext
src/main/java/com/example/demo
├── config          # Configuration files (WebSocket and Excel loader)
├── controller      # REST API endpoints
├── entity          # JPA entity classes
├── enums           # Enums for message statuses
├── repository      # JPA repositories
├── service         # Business logic
