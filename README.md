# Screening Service

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
   **Build the application**

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


## Continuous Integration and Deployment

GitHub Actions has been configured to run the tests on every push to the master branch
and build a docker image for production.
The actions are located in the `.github/workflows` directory.

## Enabling Production-ready Features

- Spring Boot Actuator for health checks
- Logging to file and setup distributed logging with ( for example ELK stack)
- Custom metrics collection with Micrometer
- Distributed tracing with Spring Cloud Sleuth and Zipkin
- Secure endpoints for TSL v1.2/v1.3 only access

## Author

- Mamadou Diallo (mhalioud@gmail.com)
   