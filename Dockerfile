FROM quay.io/quarkus/quarkus-micro-image:2.0

WORKDIR /work/
COPY target/*-runner.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
