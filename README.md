# Mini Bank

A Spring Boot-based backend application for basic banking operations, including account management and transaction processing.

## Project Structure

- **src/main/java/com/ashisht/mini_bank/controller**: REST controllers and global exception handler
- **src/main/java/com/ashisht/mini_bank/service**: Business logic and service interfaces/implementations
- **src/main/java/com/ashisht/mini_bank/repository**: Spring Data JPA repositories
- **src/main/java/com/ashisht/mini_bank/entity**: JPA entity classes
- **src/main/java/com/ashisht/mini_bank/web/request**: Request DTOs
- **src/main/java/com/ashisht/mini_bank/web/response**: Response DTOs
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

## Documentation

See [`docs/architecture.md`](docs/architecture.md) for a detailed explanation of the architecture, layers, and diagrams.

---

**Author:** Ashish Thakur
