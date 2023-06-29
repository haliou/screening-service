# Screening Service

Webservice to compare names against a list of sanctioned entities.

## Table of Contents

- [Tech Stack](#tech-stack)
- [Setup](#setup)
- [Testing](#testing)
- [Usage](#usage)
- [Continuous Integration/Deployment](#Continuous-Integration-and-Deployment)
- [Enabling Production-ready Features](#enabling-production-ready-features)
- [Author](#author)

## Tech Stack

- **Language**: Kotlin
- **Framework**: Spring Boot
- **Build Tool**: Gradle
- **Java Version**: 17
- **Testing Framework**: JUnit, Spring Boot Test
- **CI/CD**: GitHub Actions

## Setup

1. **Clone the repository**

   **Please note the application is in a private repo so as to avoid 3rd party unauthorised access**

   ```bash
   git clone https://github.com/haliou/moon-rover-api.git
   cd moon-rover-api
   ```
2. **Build the application**

```bash
./gradlew clean build
```

3. **Run the application**

   ```bash
   ./gradlew bootRun
   ```

The service should now be available at http://localhost:8080

## Testing

Unit tests and integration tests have been written for the service.

Run the tests with:

   ```bash
   ./gradlew test
   ```

## Usage

The Screening service supports the following operations

1. Verify the provided name if a match exist against the database

```bash
 curl http://localhost:8080/api/v1/screening?name=Bin%20Laden
```

2. Add a new entry into the database
```bash
curl -X POST "http://localhost:8080/api/v1/screening" \
     -H "content-type: application/json" \
     -d "{\"name\": \"Bin Laden\"}"
```

3. Update an existing entry
```bash
curl -X PATCH "http://localhost:8080/api/v1/screening" \
     -H "content-type: application/json" \
     -d "{\"oldName\": \"Bin Laden\",\"newName\": \"Saddam Hussein\"}"
```

4. Delete an entry
```bash
 curl -X DELETE http://localhost:8080/api/v1/screening?name=Bin%20Laden
```

## Continuous Integration and Deployment

GitHub Actions has been configured to run the tests on every push to the master branch
and build a docker image for production.
The actions are located in the `.github/workflows` directory.

## Enabling Production-ready Features

- Use a redis cache for storing/retrieving results and avoid database hit for duplicate requests.
- Spring Boot Actuator for health checks
- Logging to file and setup distributed logging with ( for example ELK stack)
- Custom metrics collection with Micrometer
- Distributed tracing with Spring Cloud Sleuth and Zipkin
- Secure endpoints for TSL v1.2/v1.3 only access

## Author

- Mamadou Diallo (mhalioud@gmail.com)
   