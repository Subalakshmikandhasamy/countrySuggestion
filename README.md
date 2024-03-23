# Counties API

This API provides functionality to suggest counties based on provided query strings.

## Getting Started

To get started with this API, follow the instructions below.

### Prerequisites

- Java version: 17
- Gradle: 7.5.1
- Spring Boot version: 3.2.4

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
    ```
2. Navigate to the project directory:
    ```bash
    cd <project-directory>
    ```
3. Build the project:
    ```bash
    ./gradlew clean build
    ``` 
4. Navigate to the build/libs directory:
    ```bash
    cd build/libs
    ```
5. Run the application:
    ```bash
    java -jar country-0.0.1-SNAPSHOT.jar
    ```
## Endpoints

### Suggest Counties

- **Description:** Get suggested counties based on the provided query string.
- **Method:** GET
- **Path:** `/suggest`
- **Parameters:**
    - `q`: County name and/or state fragment. *(required)*
- **Responses:**
    - `200 OK`: Returns an array of county suggestions.

## Example
```bash
curl -X GET "http://localhost:3000/counties/suggest?q=cowlitz, wa" -H "accept: application/json"
 ```
## Built With

- Spring Boot: Framework for building Java-based applications
- Gradle: Dependency Management

## Authors

- Subalakshmi K