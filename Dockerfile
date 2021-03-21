FROM openjdk:11
ARG JAR_FILE=target/units-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} units-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/units-0.0.1-SNAPSHOT.jar"]
