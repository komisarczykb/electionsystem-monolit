# Election System

A simple Spring Boot application for registering votes across multiple elections — manage voters, elections and their options, and let each voter cast a single vote per active election.

## Tech stack

- **Java 21**
- **Spring Boot 4.0.0** — WebMVC, Data JPA, Validation, Cache
- **H2** in-memory database
- **Caffeine** caching
- **Lombok**
- **Maven** (wrapper included)

## Features

- Create elections and manage their options (candidates / choices)
- Register voters — single or in bulk — and update their status
- Cast votes with validation:
  - a voter can vote only once per election
  - the voter must be active (not blocked)
  - the election must be active
- View vote counts and per-election vote lists
- Global exception handling with structured error responses

## Architecture

Standard layered structure:

```
controller → service → repository → entity
```

| Package | Responsibility |
|---------|----------------|
| `controller/` | REST endpoints |
| `service/` + `service/impl/` | Business logic |
| `repository/` | Spring Data JPA interfaces |
| `entity/` | JPA entities (`Election`, `ElectionOption`, `Voter`, `ElectionVotes`) |
| `dto/` | Request / response DTOs |
| `mapper/` | Entity ↔ DTO mapping |
| `exception/` | Domain exceptions + `GlobalExceptionHandler` |

## API overview

Base path prefixes: `/elections/v1` and `/voters/v1`. IDs are UUIDs.

### Elections

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/elections/v1` | Create an election |
| GET    | `/elections/v1` | List all elections |
| GET    | `/elections/v1/{id}` | Get a single election |
| DELETE | `/elections/v1/{id}` | Delete an election |
| POST   | `/elections/v1/{id}/options` | Add options to an election |
| GET    | `/elections/v1/{id}/options` | List an election's options |
| DELETE | `/elections/v1/options/{optionId}` | Delete an option |
| POST   | `/elections/v1/{id}/vote` | Cast a vote |
| GET    | `/elections/v1/{id}/votes/count` | Get total vote count |
| GET    | `/elections/v1/{id}/votes` | List all votes for an election |

### Voters

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/voters/v1` | Register a voter |
| POST   | `/voters/v1/bulk` | Register multiple voters |
| PATCH  | `/voters/v1/{id}/status` | Update a voter's status |
| GET    | `/voters/v1` | List all voters |
