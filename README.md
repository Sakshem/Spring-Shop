# Spring-Shop - A Microservices Project

This repository contains a microservices project that implements a set of services for managing products, orders, inventory, and notifications. The project is built using Java, Spring Boot, Maven, MongoDB, MySQL, Apache Kafka, Resilience4J, and Docker. It demonstrates both synchronous and asynchronous communication between services.

## Services

### 1. Product Service

- **Description:** This service acts as a product catalog and allows the creation and viewing of products.
- **Technology:** Java, Spring Boot, MongoDB
- **Service Endpoint:** `/api/product`

### 2. Order Service

- **Description:** This service is responsible for creating orders for products.
- **Technology:** Java, Spring Boot, MySQL
- **Service Endpoint:** `/api/order`

### 3. Inventory Service

- **Description:** The Inventory Service checks whether a product is in stock or not.
- **Technology:** Java, Spring Boot, MySQL
- **Service Endpoint:** `/api/inventory`

### 4. Notification Service

- **Description:** The Notification Service sends notifications after an order is placed.
- **Technology:** Java, Spring Boot, Apache Kafka
- **Service Endpoint:** `/api/notification`

## Communication

- Synchronous communication is used between the Product Service, Order Service, and Inventory Service through RESTful APIs.
- Asynchronous communication is implemented using Apache Kafka for communication between the Order Service and Notification Service.

## Technologies Used

- Java
- Spring Boot
- Maven
- MongoDB (Product Service)
- MySQL (Order Service and Inventory Service)
- Eureka (A service registry and discovery server used for locating and connecting to microservices)
- Apache Kafka (Order Service and Notification Service)
- Resilience4J (for fault tolerance)
- Docker (for containerization)

### Services Model Representation
![image](https://github.com/Sakshem/microservices-project/assets/61968230/56e14da3-844c-41c7-ac50-be61529bab2a)
