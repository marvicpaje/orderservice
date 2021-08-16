FROM anapsix/alpine-java:latest 
COPY target/order-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 9743 
ENTRYPOINT java -jar ./app.jar