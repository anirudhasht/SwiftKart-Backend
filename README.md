# 🚀 SwiftKart Backend — Event-Driven Microservices E-Commerce System

SwiftKart is a production-oriented e-commerce backend built using Spring Boot microservices, Kafka-based asynchronous communication, and cloud deployment on AWS EC2. This project focuses purely on backend engineering — service decoupling, async event processing, and scalable system design.

> Services don't call each other directly. An order being placed never synchronously touches inventory. If Inventory Service goes down, orders still go through. That's the design.

## 🏗️ Architecture Highlights
- Microservices design with clear separation of responsibilities
- Event-driven communication via Kafka — zero direct service-to-service HTTP calls
- JWT-based authentication with role-based authorization
- Stateless services enabling horizontal scalability
- Dockerized for environment consistency, deployed on AWS EC2

## 🧩 Services

### 🔐 User Service
- User registration and login
- JWT token generation and validation
- Centralized auth and authorization logic

### 📦 Order Service
- Order creation and management
- Publishes `OrderCreated` events to Kafka
- Never directly calls Inventory Service — fully decoupled

### 🏪 Inventory Service
- Consumes Kafka events asynchronously
- Updates product stock based on order events
- Designed to handle failures independently

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Backend | Spring Boot 3+, Spring Security, Spring Data JPA, Lombok |
| Messaging | Apache Kafka (Producer & Consumer) |
| Database | PostgreSQL |
| Auth | JWT (stateless, role-based) |
| Deployment | Docker, Docker Compose, AWS EC2 |
| Testing | Postman |

## 🚀 Run Locally

All services, Kafka, Zookeeper, and PostgreSQL spin up in containers — no local installs needed.

```bash
git clone https://github.com/anirudhashrit/SwiftKart-Backend
cd SwiftKart-Backend
docker-compose up --build
```

## ☁️ Cloud Deployment

Spring Boot services are deployed on AWS EC2. Kafka runs locally via Docker due to free-tier constraints — in production this maps directly to AWS MSK or Confluent Cloud with no code changes required.

## 📌 API Docs

Full Postman collection covering auth flows, order creation, and inventory checks:
👉 [View Collection](https://documenter.getpostman.com/view/43458909/2sB3dSR9N6)

## 🧪 Key Engineering Concepts Demonstrated
- Microservices architecture with independent deployability
- Event-driven design and async processing via Kafka
- Service fault isolation — downstream failures don't cascade
- JWT-based stateless security
- Containerized, cloud-aware backend design

## 🔮 What's Next
- [ ] API Gateway (Spring Cloud Gateway / Nginx)
- [ ] Dead-letter queues + Kafka retry logic
- [ ] Centralized logging with ELK stack
- [ ] Redis caching layer
- [ ] Kubernetes deployment manifests

## 👨‍💻 Author
**Anirudh Ashrit** — Backend Developer · Spring Boot · Distributed Systems · AI Backends  
[GitHub](#) · [LinkedIn](#) · [LeetCode](#)
