FROM amazoncorretto:17

WORKDIR /app

COPY itsystem-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "itsystem-0.0.1-SNAPSHOT.jar"]
