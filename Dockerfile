FROM amazoncorretto:17
COPY target/um_api-1.0.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]