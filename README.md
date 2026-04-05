#🚀 GitHub User Activity API
A modern Spring Boot 4.0.5 REST API that acts as a proxy to fetch and format recent GitHub user activity. This project is part of the roadmap.sh backend challenges.

🛠 Features
RESTful Endpoint: Fetch activity via a clean GET request.

Data Transformation: Converts complex GitHub JSON into human-readable activity summaries.

Modern Java: Utilizes Java 25 features like records and switch expressions.

Robust Architecture: Implements a clean Controller-Service-Implementation pattern.

Graceful Handling: Identifies "some" commits when specific GitHub payload data is abstracted.

🏗 Tech Stack
Framework: Spring Boot 4.0.5

Language: Java 25

Client: Spring `RestClient`

Build Tool: Maven

🚀 Getting Started
Prerequisites

JDK 25

Maven 3.x

Installation

Clone the repository:

```bash
git clone https://github.com/massaotani/github-activity.git
cd github-activity
Run the application:
```

```bash
./mvnw spring-boot:run
```
The server will start on `http://localhost:8080`.

📡 API Usage
Get User Activity

Fetches the last 10 public events for a specific GitHub user.

Endpoint: `GET /api/github/{username}`

Example Request:

```bash
curl http://localhost:8080/api/github/massaotani
Example Response:
```
```
- Pushed some commits to massaotani/task-manager-api
- Created massaotani/task-manager-api
- Starred spring-projects/spring-boot
- opened a new issue in some-repo/test
```

📂 Project Structure
`controller/`: Handles incoming HTTP requests.

`service/`: Defines the business logic interface.

`service/impl/`: Contains the `RestClient` logic and GitHub API integration.

`model/`: Defines the `GitModel` using Java Records for efficient JSON mapping.