FROM ubuntu
RUN apt-get update
RUN apt-get install -y openjdk-17-jre
COPY ./build/libs/need-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
