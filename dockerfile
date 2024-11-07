FROM openjdk:17
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar ski.jar
ENTRYPOINT ["java", "-jar", "ski.jar"]