FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/gateway.jar gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]