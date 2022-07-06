FROM openjdk:11
COPY build/libs/project-0.0.1-SNAPSHOT.jar project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/project-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=test"]