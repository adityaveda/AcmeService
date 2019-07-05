# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml install -DskipTests

# # #execute jar
#  FROM gcr.io/distroless/java  
#  COPY --from=build /usr/src/app/target/AcmeService-1.0-SNAPSHOT.jar /usr/app/AcmeService-1.0-SNAPSHOT.jar
# # EXPOSE 8080  
# # ENTRYPOINT ["java","-jar","/usr/app/AcmeService-1.0-SNAPSHOT.jar"]  

FROM openjdk:8
ADD target/AcmeService-1.0-SNAPSHOT.jar AcmeService-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","AcmeService-1.0-SNAPSHOT.jar"]