FROM ubuntu
RUN apt-get update && apt-get upgrade
RUN apt-get install openjdk-17-jre
COPY ./build/libs/need-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
