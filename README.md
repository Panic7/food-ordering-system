# Restaurant Ordering System

## Project Overview

The Restaurant Ordering System is a Spring Boot-based application designed to facilitate the process of ordering food
and drinks through a Command Line Interface (CLI). This project demonstrates the use of modern Java features, Spring
Boot, and various other technologies to create a robust and interactive CLI application.

## Key Concepts

- **Spring Boot**: A framework that simplifies the development of Java applications by providing pre-configured
  templates and reducing boilerplate code.
- **Java 21**: The latest version of Java, which includes new features and enhancements such as pattern matching,
  records, and more.
- **Maven**: A build automation tool used for managing project dependencies and building the project.
- **Lombok**: A library that helps reduce boilerplate code by generating getters, setters, and other methods at compile
  time.
- **MapStruct**: A code generator that simplifies the mapping between Java bean types based on a
  convention-over-configuration approach.
- **Liquibase**: A database schema change management tool that allows you to manage and track database changes.

## Features

- **Dynamic Menu Items**: Menu items (e.g., meals, drinks, desserts) are unified under a single MenuItem structure,
  supporting attributes like name, price, category, cuisine, and addons.
- **Addons Support**: Addons (e.g., ice cubes, lemon) can be attached to any menu item, enabling flexibility.
- **Extendable Design**: The architecture allows for seamless integration of new features, such as additional cuisines,
  categories, or predefined menu carts.
- **Interactive CLI**: Customers can place orders, select addons, and review total costs through a user-friendly
  command-line interface.
- **Unit Testing**: Comprehensive tests ensure the correctness and reliability of core features.
- **Data Generation**: Data generation is performed under a transaction, ensuring that if something goes wrong, the
  transaction will roll back, maintaining data integrity.

## Technical Information

### PlantUML Diagram

The following PlantUML diagram illustrates the project's architecture and key components:

- [Class Diagram](class-diagam.puml)
- [CLI Flow Diagram](cli-flow.puml)

### Dependencies

- **Spring Boot**: 3.4.0
- **Java**: 21
- **Lombok**: 1.18.36
- **MapStruct**: 1.6.3
- **Liquibase**: 4.30.0
- **H2 Database**: 2.3.232
- **Hashids**: 1.0.3

### Profiles

- **Default Profile**: The `test-data` profile is active by default if no other specified. This profile generates test
  data for the application.
