# units
___
### To run the Dockerized application (Docker has to be installed for this step):

    > docker run -p 8080:8080 rossen12/units 

### To run the application without Docker (Maven and JDK 11 have to be installed for this step), from the `units` directory:

    > mvn clean package

    > java -jar target/units-0.0.1-SNAPSHOT.jar

### To test the running application hit the following URLs:

+ http://localhost:8080/units/si?unit=degree

+ http://localhost:8080/units/si?unit=degree/minute

+ http://localhost:8080/units/si?unit=(degree/(minute*hectare))

+ http://localhost:8080/units/si?unit=ha*°
