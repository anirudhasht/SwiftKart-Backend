SwiftKart — E-Commerce Microservices Backend
A production-oriented backend built with Spring Boot, Kafka, and AWS EC2. Three independent services communicating asynchronously — no direct service-to-service HTTP calls.

Services

User Service — registration, login, JWT auth
Order Service — order management, publishes OrderCreated events to Kafka
Inventory Service — consumes Kafka events, updates stock asynchronously

The Inventory Service never directly talks to Order Service. If inventory goes down, orders still go through. That's the point of the design.

Tech Stack
LayerTechnologyBackendJava 17, Spring Boot 3+, Spring Security, JPAMessagingApache KafkaDatabasePostgreSQLDeploymentDocker + Docker Compose, AWS EC2

Run Locally
bashgit clone https://github.com/anirudhashrit/SwiftKart-Backend
cd SwiftKart-Backend
docker-compose up --build
Spins up all services, Kafka, Zookeeper, and PostgreSQL in containers.

API Docs
Full Postman collection — auth, orders, inventory:
👉 View Collection

What's Next

API Gateway
Dead-letter queues + Kafka retry logic
Redis caching
Kubernetes deployment


Anirudh Ashrit · GitHub · LinkedIn · LeetCode
