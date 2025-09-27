FROM openjdk:26-jdk
ADD ExpenseTracker/target/ExpenseTracker.jar /ExpenseTracker.jar
ENTRYPOINT ["java", "-jar", "ExpenseTracker.jar"]
