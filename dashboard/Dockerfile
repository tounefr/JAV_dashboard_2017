FROM ubuntu:xenial

RUN apt-get update -y
RUN apt-get install openjdk-8-jdk maven -y

COPY . /tmp/app

RUN cd /tmp/app && mvn package
RUN cp /tmp/app/target/dashboard-*.jar /app.jar
RUN rm -rf /tmp/app

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
