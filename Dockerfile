FROM ubuntu
RUN sudo apt-get update
RUN sudo apt-get install openjdk-17-jre
COPY ./build/libs/need-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
