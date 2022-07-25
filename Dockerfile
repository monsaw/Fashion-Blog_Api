FROM openjdk:17
EXPOSE 8080
ADD target/Fashion-Blog-Api.jar Fashion-Blog-Api.jar
ENTRYPOINT ["java" , "-jar" , "/Fashion-Blog-Api.jar"]
