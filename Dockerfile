FROM openjdk:26-jdk
WORKDIR /app
ENV SERVER_PORT=8080
COPY ExpenseTracker/target/ExpenseTracker.jar /ExpenseTracker.jar
ENTRYPOINT ["java", "-jar", "/ExpenseTracker.jar","-server.port=${SERVER_PORT}"]
EXPOSE 8080
