# Project Documentation

## Overview

This project provides a structured system for managing donations, library services, and events. Built using a layered architecture, it organizes core functionalities across packages including `Controller`, `MODEL`, `DBUtil`, `DTO`, and `View`. Testing is implemented using JUnit, and the database is connected via MySQL.

## Project Structure

### Root Files

- **README.md**: This documentation file, providing an overview of the project.
- **build.xml**: Apache Ant build script, automating build and deployment processes.
- **manifest.mf**: Defines manifest attributes for creating a JAR file.

### Libraries

- **libs**: Contains external dependencies:
  - `junit-4.13.2-javadoc.jar`: Documentation for the JUnit framework used for unit testing.
  - `mysql-connector-j-8.3.0.jar`: MySQL Connector library for handling database connections.

### Project Configuration

- **nbproject**: NetBeans configuration files, including:
  - `build-impl.xml`: Internal build configuration file.
  - `project.properties`: Project-specific properties.
  - `project.xml`: General project metadata.

### Source and Output Code

- **src**: Contains the main source code organized by modules:
  - `Controller`: Manages donation and library services logic.
  - `MODEL`: Implements the Data Access Object (DAO) pattern, with sub-packages for:
    - `DAO`: Data access layer classes, handling database CRUD operations.
    - `Patterns`: Implements common design patterns:
      - `decorator`: Classes for enhancing donations with specific features.
      - `factory`: Factory classes for event creation.
      - `singleton`: Singleton pattern for database connections.
  - `DBUtil`: Contains utilities for managing database connectivity.
  - `DTO`: Data Transfer Objects that represent data structures within the project.
  - `View`: Handles the project’s view-related classes, like command-line displays.
  - `constatns`: Configuration files, e.g., `constants.properties`.

- **out**: Contains the compiled classes, matching the `src` structure for easy navigation.

- **test**: Directory for unit tests, including:
  - `generalTest`: General tests for main classes.
  - `patterns`: Tests for implemented design patterns.

## Prerequisites

- **Java**: JDK 8 or higher.
- **MySQL**: Database server for data persistence.
- **Apache Ant**: For running the build script.
- **JUnit**: Included in `libs` for running unit tests.

## Database Design

The database design for this project is maintained and visualized on **DB Schema**. For a full database schema and to modify relationships, refer to [our schema on dbdiagram.io](https://dbdiagram.io/d/test-671eed8497a66db9a37220e7).

### Important Notes on Database Management

- Use the latest `SQL` file uploaded to the **main branch** of the project repository for the most up-to-date schema.
- Do not use automatically generated SQL exports from dbdiagram.io; always refer to the SQL file on the main branch to avoid inconsistencies.
- Ensure that any modifications to the database structure are saved and updated on the main branch.

## Setting Up

1. Clone the repository.
2. Ensure the MySQL Connector (e.g., `mysql-connector-j-8.3.0.jar`) is available in the `libs` directory.
3. Update `constants.properties` in `src/constatns` with the database configuration:
   ```properties
   jdbc.url=jdbc:mysql://localhost:3307/sdp_database
   jdbc.username=YOUR_USERNAME
   jdbc.password=YOUR_PASSWORD
