# Start with Java
FROM openjdk:8

# copies built .jar into image with the name app.jar
COPY ./target/book-service-0.0.1-SNAPSHOT.jar app.jar

# Documents the port that is exposed by the underlying app
# Does not functionally expose a port
EXPOSE 8010

ENTRYPOINT ["java", "-jar", "./app.jar"]