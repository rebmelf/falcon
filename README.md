# Falcon Test Application

The application can persist and expose a dummy json on a websocket. It has two endpoints and a basic html page to test.

The application can be built with gradle, and uses redis and mysql (instead mysql, h2 is used for unit testing).

## REST API documentation
The REST API endpoints are documented with using Swagger, the yaml file can be found at: /src/main/resources/swagger/specification.yaml 
Currently two endpoints are available:
- GET /api/v1/services/tumblr?page={0}&size={1} : The getAll interface, both size and page parameters are required. Currently there is no upper bound for the parameter values, in the future a property should be added to limit the size parameter.  
- POST /api/v1/services/tumblr : The record creator interface, the "name" parameter works as an external key.
  Example request:
```json
{
  "name": "Pizzambasador",
  "type": "Food and Drink",
  "popularity": 3
}
```
The following header is required for the calls:
```
Content-Type: application/json
```

## Database setup
The used database was mysql, the initialization script can be located at /src/main/resources/db/initial/DB_initialization.sql .
Flyway is used for creating the database modifications, so the ddl-auto parameter is set to 'validate'. The naming conventions of the migration scripts are defined in /src/main/resources/db/migration/info.txt .
The flyway is turned out for the unit tests, and a h2 in memory database is used.

I used Hibernate and JPAs for the database operations.

## Configuration
General configuration can be found under the application.yml There are three specific profiles defined, local for development, unittest for the unit test runs, and docker for running the application in docker.

## Layers
Upon the Repositories, a RepositoryService layer is added, which should hide not required functions, and can work as a mapper too if required. If more complex business logic is added, they can be used as building blocks for the business Service layer. 
The RepositoryService has a private method for saving the entity, the repository.save() or repository.saveAndFlush() methods should be only called from there.

## Logging and errors
The Log and error messages are stored in the same place, to make later easier the localization. The logback-spring.xml contains the configuration of the different spring profiles (currently it's the same for every profile).
If an error can reach the Controller classes, they are now encapsulated into a ServiceException, and handled by the RestAPIExceptionHandler class.

## Redis and websocket

The websocket can be reached on http://localhost:8080. Redis uses the default configuration.

## Running the application locally
IDEA creates a separate module for the generated/main/test subsets. The problem is, that the dependencies are not set properly.
To make sure, that the tests or the application runs without problem either one of this two workaround should be used:
- When importing the project, disable the 'Create separate module for each subset' option.
- When something does not find the io.falcon.falcontest.model.QTumblrAccount class, please add the generated module as a dependency to the main and test modules.

The only vm option which should be used is: -Dspring.profiles.active={profileName} eg. -Dspring.profiles.active=local

## Docker
The docker-compose.yml file is located at docker/docker-compose.yml, so the docker-compose up command works from that directory.
I used OSX during development, here, everything is exposed as localhost, so for example an API should be called as: http://localhost:8080/api/v1/services/tumblr

For building the application, please run ./gradlew build docker from the root of the project.


## UI

The html can be found at http://localhost:8080/ , the Connect button connects to the websocket, then the new message is shown after each send. 
## Security
No security was used, so the spring security is not turned on, and redis password is not set. Although the user is created for the docker, currently the root user is used to connect the database there.

## Known issues
- The is a small chance, that the IdGenerator implementation creates the same id if they are generated in the same millisecond
- Sometimes, if a computer is not fast enough the async test of io.falcon.falcontest.websocket.WebSocketIntegrationTest.sendAndReceiveMessage() can fail, and has to be repeated.
  

 

