FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
COPY ./target/income-product-service-0.0.1-SNAPSHOT.jar income-product-service.jar
ENTRYPOINT ["java","-jar","income-product-service.jar"]