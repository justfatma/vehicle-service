# vehicle-service
Calls external API, returns response based on filters entered

## Used Software
Java, Spring Boot, Maven, Spring Batch, Restful API, JUnit, Log4j

## Request Url
http://localhost:8080/vehicles

## Sample request bodies: 

{"dockAvailable":true,
"bikeAvailable":true,
"renting":true,
"returning":true,
"stationId": "PARIS-53-RUE-DES-ARCHIVES-75003-FANTASMO",
"price": 1
}

{"dockAvailable":true,
"bikeAvailable":true,
"renting":true,
"returning":true
}

## Spring Batch
In terms of **performance** , data is pulled from external APIs at certain times using Spring Batch.
This time is parametric and can be set within the code. For example, the information about the stations was taken every 5 minutes, 
but because the price did not change very often, it was taken every 1 hour.

## Log4j
Log4j was used for **logging**. Only the console log is printed so that no additional file creation is needed.
It is currently logging at INFO level, if desired, the level can be changed from the log4j.properties file.

## JUnit
**Unit tests** were written in VehicleServiceTests.java class and run successfully.


