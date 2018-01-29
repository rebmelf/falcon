# Falcon Test Application

## REST API documentation
The REST API endpoints are documented with using Swagger, the yaml file can be found at: /src/main/resources/swagger/specification.yaml 

## Database setup

The database initialization script can be located at /src/main/resources/db/initial/DB_initialization.sql .
Flyway is used for creating the database modifications, so the ddl-auto parameter is set to 'validate'. The naming conventions of the migration scripts are defined in /src/main/resources/db/migration/info.txt .


