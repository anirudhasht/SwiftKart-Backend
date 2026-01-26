🚀 SwiftKart Backend
Event-Driven Microservices E-Commerce System

SwiftKart is a production-oriented e-commerce backend built using Spring Boot microservices, Kafka-based asynchronous communication, and cloud deployment on AWS EC2.
The project demonstrates real-world backend design, service decoupling, and scalable system architecture.

This project focuses on backend engineering best practices rather than UI, making it suitable for learning and showcasing distributed systems concepts.

🏗️ System Architecture
                    ┌────────────────────┐
                    │   Client / Frontend │
                    │ (Postman / UI)      │
                    └─────────┬──────────┘
                              │ HTTP (REST)
                              ▼
                   ┌──────────────────────┐
                   │   API Gateway        │
                   │ (Conceptual / Nginx) │
                   └─────────┬────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        ▼                    ▼                    ▼
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│ User Service │     │ Order Service│     │Inventory Svc │
│ (Spring Boot)│     │ (Spring Boot)│     │ (Spring Boot)│
│ JWT Auth     │     │ Order Mgmt   │     │ Stock Mgmt   │
└──────┬───────┘     └──────┬───────┘     └──────┬───────┘
       │                    │                    │
       │                    │ Kafka Event        │
       │                    │ (OrderCreated)     │
       │                    ▼                    │
       │            ┌────────────────────┐       │
       │            │      Kafka         │◄──────┘
       │            │ (Local / Docker)   │
       │            └────────────────────┘
       │
       ▼
┌─────────────────────────────────────────────────┐
│               PostgreSQL Database                │
│ (Local / EC2-hosted depending on environment)    │
└─────────────────────────────────────────────────┘

🧠 Architecture Highlights

Microservices-based design with clear separation of responsibilities

Event-driven communication using Kafka to decouple services

JWT-based authentication & authorization

Stateless services enabling horizontal scalability

Containerized deployment using Docker

Cloud deployment on AWS EC2 (free-tier compatible)

🧩 Services Overview
🔐 User Service

User registration & login

JWT token generation and validation

Centralized authentication logic

📦 Order Service

Order creation and management

Publishes OrderCreated events to Kafka

Does not directly depend on Inventory Service (loose coupling)

🏪 Inventory Service

Consumes Kafka events asynchronously

Updates product stock based on order events

Designed to handle service failures independently

⚙️ Tech Stack
Backend

Java 17

Spring Boot 3+

Spring Security (JWT)

Spring Data JPA

Apache Kafka (Producer & Consumer)

Lombok

Database

PostgreSQL

Build & Deployment

Docker & Docker Compose

AWS EC2 (Free Tier)

Postman (API testing)

📁 Project Structure
SwiftKart-Backend/
 ├── user-service/
 ├── order-service/
 ├── inventory-service/
 ├── docker-compose.yml
 └── README.md

🚀 Deployment Strategy
Local / Containerized Environment

All services run via Docker Compose

Kafka + Zookeeper run locally in containers

PostgreSQL runs as a containerized service

Cloud Deployment (AWS EC2)

Spring Boot services deployed on AWS EC2

Kafka used locally due to free-tier constraints

In a production setup, Kafka can be replaced with:

AWS MSK

Confluent Cloud

Cloud-native messaging services

⚠️ This approach reflects real-world industry practices where managed services are preferred in production.

📌 API Documentation (Postman)

🔗 Postman Collection:
👉 https://documenter.getpostman.com/view/43458909/2sB3dSR9N6

Includes:

Auth APIs

Order APIs

Inventory APIs

End-to-end flow testing

🧪 Key Engineering Concepts Demonstrated

Microservices architecture

Event-driven systems

Asynchronous processing

Service decoupling

JWT-based security

Dockerized deployments

Cloud-aware backend design

🔮 Future Enhancements

API Gateway implementation (Spring Cloud Gateway / Nginx)

Centralized logging & monitoring

Kubernetes-based deployment

Retry & dead-letter queues in Kafka

Caching with Redis

👨‍💻 Author

Anirudh Ashrit
Backend Developer | Spring Boot | Distributed Systems
