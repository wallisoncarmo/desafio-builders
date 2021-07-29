FROM openjdk:11
VOLUME /tmp
ADD ./target/client-service.jar client-service.jar
ENTRYPOINT ["java","-jar","/client-service.jar"]