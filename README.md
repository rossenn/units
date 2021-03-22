# units
___
### To run the Dockerized application (it is already pushed to Docker Hub):

    > docker run -p 8080:8080 rossen12/units 

### To build the Docker container, from the project directory:

    units> mvn clean package

    units> docker build -t rossen12/units . 

### To run the application without Docker, from the project directory:

    units> mvn clean package

    units> java -jar target/units-0.0.1-SNAPSHOT.jar

### To test the running application hit the following URLs:

+ http://localhost:8080/units/si?units=degree

+ http://localhost:8080/units/si?units=degree/minute

+ http://localhost:8080/units/si?units=(degree/(minute*hectare))

+ http://localhost:8080/units/si?units=ha*°
