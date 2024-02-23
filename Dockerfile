FROM amazoncorretto:17
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ./target/itsystem-0.0.1-SNAPSHOT.jar itsystem.jar
ENTRYPOINT ["java", "-jar", "itsystem.jar"]
EXPOSE 8080