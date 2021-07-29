FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/discovery.jar discovery.jar
ENTRYPOINT ["java","-jar","/discovery.jar"]