FROM openjdk
COPY ./build/libs/CardZone-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java", "-jar", "CardZone-0.0.1-SNAPSHOT.jar"]
