# Tutorial Management Application

This is a tutorial management application built with Angular and Spring Boot. It allows you to manage students, courses, and tutorials.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed the latest version of [Node.js and NPM](https://nodejs.org/en/download/).
- You have installed the latest version of [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org/download.cgi).
- You have a Windows/Linux/Mac machine.
- You have PostgreSQL running on port 5432 on your local machine.

## Installing Tutorial Management Application

To install the Tutorial Management Application, follow these steps:

1. Clone the repository: `git clone https://github.com/rakesh-vardan/tutorial-application.git`
2. Navigate into each project directory: `cd tutorial-application`
3. Install the dependencies (after changing to respective directories):
   - For Spring Boot applications: `mvn install`
   - For Angular application: `npm install`

## Using Tutorial Management Application

To use the Tutorial Management Application, follow these steps:

1. Start the services in the following order:
   - Courses Service: `mvn spring-boot:run`
   - Students Service: `mvn spring-boot:run`
   - Tutorial Service: `mvn spring-boot:run`
   - Tutorial Frontend: `npm start` OR `ng serve`
2. Open your web browser and visit `http://localhost:4200`
