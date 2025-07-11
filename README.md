﻿<h1 align="center">Microservice Example</h1>

# 🛍️ Product Service

This is a simple microservice built with Spring Boot for managing products. It demonstrates basic CRUD functionality with DTOs, validation, mapping, and testing.

## 📚 Table of Contents

- [Technologies used](#-technologies-used)
- [Entities](#-entities)
  - [Product](#product)
- [Endpoints](#-endpoints)
- [Test Scenarios and Results](#test-scenarios-and-results)
- [License](#license)

## 🚀 Technologies used

- Java 17
- Maven
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- MapStruct
- Flyway
- Swagger (SpringDoc OpenAPI)
- JUnit 5 + Mockito

## 📦 Entities

### Product

```java
@Entity
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private double price;
}
```



## 📡 Endpoints

| Method | Endpoint         | Description            |
| ------ | ---------------- | ---------------------- |
| POST   | /products      | Create a new product   |
| GET    | /products      | Retrieve all products  |
| GET    | /products/{id} | Retrieve product by ID |


## 📩 Example Request json
POST /products

```
{
"name": "MacBook Pro",
"price": 1999.99
}
```

## 📤 Example Response json

```
{
"id": 1,
"name": "MacBook Pro",
"price": 1999.99
}
```


## ✔️Test Scenarios and Results
✅ Successful product creation

✅ Validation errors (e.g. empty name, negative price)

✅ Get product by ID (found & not found)

✅ Get all products

<p align="center">
  <img src="images/coverage.png" alt="Coverage" />
</p>

Run tests with:

```
mvn test
```

##  License
This project is licensed under the MIT license. Feel free to edit and distribute this template as you like.
