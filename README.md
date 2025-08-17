# Mini Bank

A Spring Boot-based backend application for basic banking operations, including account management and transaction processing.

## Project Structure

- **src/main/java/com/ashisht/mini_bank/controller**: REST controllers and global exception handler
- **src/main/java/com/ashisht/mini_bank/service**: Business logic and service interfaces/implementations
- **src/main/java/com/ashisht/mini_bank/repository**: Spring Data JPA repositories
- **src/main/java/com/ashisht/mini_bank/entity**: JPA entity classes
- **src/main/java/com/ashisht/mini_bank/web/request**: Request DTOs
- **src/main/java/com/ashisht/mini_bank/web/response**: Response DTOs
- **src/main/java/com/ashisht/mini_bank/mapper**: Object mappers
- **docs/**: Project documentation (see `architecture.md`)

## Build & Run

### Prerequisites
- Java 21+
- Gradle (wrapper included)
- Docker (for running Postgres, Prometheus, Grafana via docker-compose)

### Build the Project

```sh
./gradlew clean build
```

### Run the Application

```sh
./gradlew bootRun
```

The application will start on [http://localhost:8080](http://localhost:8080).

### Run Tests

```sh
./gradlew test
```

### Build the Application Image

```sh
./gradlew bootBuildImage
```

## Dockerized Infrastructure

To start Postgres, Prometheus, and Grafana:

```sh
docker-compose -f docker/docker-compose.yaml up -d
```

- Postgres: `localhost:5432`
- Prometheus: `localhost:9090`
- Grafana: `localhost:3000`

## API Endpoints

- `POST /accounts` - Create a new account
- `GET /accounts/{id}` - Get account by ID
- `POST /transactions` - Create a new transaction

## API Documentation

After starting the server, the OpenAPI (Swagger) documentation is available at:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Exception Handling

- All `IllegalArgumentException` thrown in controllers are handled globally and return HTTP 400 with the error message.

## Javadoc API Documentation

- All main classes (controllers, services, DTOs, mappers) now include class-level Javadoc comments.
- Default constructors in DTOs and mappers have Javadoc comments to ensure clean, warning-free documentation.
- Javadoc is generated automatically on every push or pull request to the main branch using GitHub Actions.
- The generated documentation is available as a downloadable artifact in the GitHub Actions workflow run.
- To view the documentation locally, run:

```sh
./gradlew javadoc
```

The output will be in `build/docs/javadoc`.

## Documentation

See [`docs/architecture.md`](docs/architecture.md) for a detailed explanation of the architecture, layers, and diagrams.

---

**Author:** Ashish Thakur
