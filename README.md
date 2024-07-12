# The Football Events Statistics Application

## Overview
This application has been creatred as a recruitment task for ALAN Systems Company. It represents a simple backend to a system managing football statistics. 
The goal for the application it to correctly handle incoming messages of two types: RESULT and GET_STATISTICS.

## Buisness Features
#### Processing RESULT Messages
- Reading data about single football event (teams, goals scored)
- Using the data to create or update statistics data of each team
- Outputting statistics (number of played events, sum of gained points, sum of goals scored, sum of goals conceded) of teams included in RESULT message

#### Processing GET_STATISTICS Messages
- Outputting statistics (three last results, average amount of goals in the team events, number of played events, sum of gained points, sum of
goals scored, sum of goals conceded) of teams included in GET_STATISTICS message

#### Technical Features
- Processing a locally-included file containing messages and generating output in the console at the start of the application \- by default, the file oryginally included with the task is processed
- Processing individual messages through REST API, generating output and sending it through the endpoint and to the console
- recognizing type of the message automatically
- error handling in case of incorrect messages both in local processing and REST communication

## Requirements
- Java 17 or higher
- Maven

## Getting Started
- Clone the repository.
- Start the application.

#### Processing Local File
Files included in the project are stored in *football-events-statistics/data* directory. 
A choosen file is automatically loaded and processed at the start of the application. 
Path to the processed file is specified in *football-events-statistics/src/main/java/dev/cm/football_events_statistics/service/LocalFileProcessingService.java*.
Change this path and restart the application in order to process different files.

#### Using REST API
The easiet way to use REST API is to use Swagger UI in an Internet browser. After starting the application open the Swagger \- the default addres is http://localhost:8080/swagger-ui/index.html#/.

#### Messages schema
All messages need to be provided as JSON. Acceptable messages are as follows:
- ```
  {
    "type": "RESULT",
    "result": {
      "home_team": "*string*",
      "away_team": "*string*",
      "home_score": *int*,
      "away_score": *int*
    }
  }
- ```
  {
    "type": "GET_STATISTICS",
    "get_statistics": {
      "teams": [*list of strings*]
    }
  }

## Architecture
The task has been implemented as Spring application with usage of Maven.

#### Project Structure
The project follows a typical layered architecture.
- Controller Layer \- contains REST API endpoint.
- DTOs (Data Transfer Objects) \- used to transfer objects from Controller to Service layer.
- Service Layer \- contains the business logic.
- Repository Layer \- interacts with the database

#### Database
H2 Database \- An in-memory database used to store data while the application is running.

#### Entities
The application uses JPA entities to map Java objects to database tables.

#### Validation
Bean Validation \- The application uses annotations such as `@NotBlanc`, `@PositiveOrZero`, and custom validators to ensure data integrity. 
Validation is performed at various layers, including DTOs and service methods.

#### Unit Tests
The application includes unit tests for individual components using JUnit and Mockito.

## Possible Improvements
The application is well prepared for extensions in certain areas.
- Currently, only information about teams and their statistics is stored as it is sufficient for the functionality described by the task.
  However, storing all events data would be resource-consuming but would would also provide great possibilites for application development.
- The list of accepted types of messages could be extended according to needs.
- Other communication protocols could be implemented \- e.g., message queue like Kafka would be a good fit for this kind of application.
- The current database \- H2 \- only stores data while the application is running. It could be replaced with solution that would persist the data.
