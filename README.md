# 🏨 Hotel & User Rating System — Distributed Microservices

A production-grade, distributed microservices system engineered with **Java 17**, **Spring Boot**, and **Spring Cloud**. The architecture incorporates centralized configuration, dynamic service registration/discovery, intelligent gateway routing, and decoupled inter-service communication to manage users, hotels, and customer ratings.

---

## 🏛️ System Architecture

```text
MicroServices_Project/
├── ConfigServer/       # Central configuration management (Spring Cloud Config)
├── ServiceRegistry/    # Service discovery registry (Netflix Eureka Server)
├── ApiGateway/         # Unified entry point, request routing, & load balancing (Spring Cloud Gateway)
├── UserService/        # Manages user accounts and aggregates ratings & hotel profiles
├── HotelService/       # Manages hotel properties, room listings, and location records
└── RatingService/      # Manages user reviews, feedback, and numeric ratings
