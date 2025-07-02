# DB2 Project

TeleConsult DB2 Project

This repository contains the source code for a small backend that stores teleconsultation data for GPs and their patients. The project is implemented in **Java 21** using **Spring Boot** and **Spring Data JPA**. A MySQL database is provided via Docker.

## Repository Layout

- `DB2/core` – domain model, repository interfaces and command classes.
- `DB2/app` – Spring Boot application layer with JPA repositories and service implementation.
- `DB2/docker-compose.yml` – launches a MySQL instance used by the application.
- `DB2/UML.gaphor` – UML diagram of the data model.

## Building

A Maven wrapper is included. To compile the modules run:

```bash
cd DB2
./mvnw clean package
```

## Running the Database

The application expects environment variables `DB_PORT` and `DB_PASSWD` for the database connection. Start a local MySQL container with:

```bash
cd DB2
docker-compose up -d
```

## Running the Application

After the database is available, start the Spring Boot application using:

```bash
cd DB2/app
../mvnw spring-boot:run
```

`application.properties` configures the database connection:

```
spring.application.name=TeleConsultDB
spring.datasource.url=jdbc:mysql://localhost:${DB_PORT}/tcdb
spring.datasource.username=tcdb
spring.datasource.password=${DB_PASSWD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
```

## Tests

Unit tests reside in `DB2/app/src/test/java`. Use Maven to execute them:

```bash
cd DB2
./mvnw test
```

## License

This project is provided under the Apache License 2.0.