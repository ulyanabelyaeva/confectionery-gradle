FROM openjdk:17-jdk-alpine
COPY ./build/libs/confectionery-gradle-0.1.jar confectionery.jar
CMD ["java","-jar","/confectionery.jar"]