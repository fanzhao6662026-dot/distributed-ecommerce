# Distributed E-commerce Platform

## 1. Architecture Overview
This project is a distributed e-commerce platform built with Spring Boot and Apache Kafka. The system follows an asynchronous event-driven architecture:
- **Order Service (Producer)**: Exposes a REST API (`POST /orders/{orderId}`) to receive customer orders and publishes events to the Kafka topic `order-events`.
- **Inventory Service (Consumer)**: Listens to the `order-events` topic and processes inventory deductions asynchronously.
- **Message Broker**: Apache Kafka (running via Docker) ensures decoupling and high throughput.

## 2. Design Decisions
- **Why Kafka?** Instead of synchronous HTTP calls, Kafka was used to decouple order creation from inventory management. If the inventory system is temporarily down, orders can still be placed without failure.
- **Topic Design**: A single topic `order-events` is used, with the `orderId` acting as the message key to ensure ordered processing per order.
- **Database**: H2 in-memory database is used for local development.

## 3. Challenges Faced and Solutions
- **Maven POM parsing errors**: Encountered issues with duplicate dependencies in `pom.xml`. Solved by restructuring the dependencies and resolving version conflicts.
- **YAML Configuration**: Faced `DuplicateKeyException` due to misaligned indentation in `application.yml`. Solved by strictly adhering to YAML spaces.
- **Git Merge Conflicts**: Encountered conflicts between local and remote repositories. Solved by using `git merge --abort` and force-pushing (`git push -f`) since the local code was the most up-to-date.
- **Windows PowerShell `curl` limitations**: Used native `Invoke-RestMethod` to test the REST API successfully.

## 4. How to Run
1. Start Kafka: `docker compose up -d`
2. Run the Spring Boot Application: `EcommerceApplication.java`
3. Test endpoint via PowerShell:
   `Invoke-RestMethod -Uri "http://localhost:8080/orders/101" -Method Post -Body "iPhone 15" -ContentType "text/plain"`
