FROM openjdk:8-jdk-alpine
LABEL maintainer="Shachindra Pandit"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} emailApp.jar
ENTRYPOINT ["java","-jar","/emailApp.jar"]