FROM amazoncorretto:17-alpine-jdk
MAINTAINER DanielAndrade <
LABEL authors="Daniel"
COPY target/gestionCitasBancarias-0.0.1-SNAPSHOT.jar Proyecto-app.jar
ENTRYPOINT ["java","-jar","/Proyecto-app.jar"]