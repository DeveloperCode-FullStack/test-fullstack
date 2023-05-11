# Base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Install MySQL client
RUN apk add --no-cache mysql-client

# Copy the JAR file into the container
COPY target/test-fullstack-0.0.1-SNAPSHOT.jar app.jar

# Copy the MySQL Connector/J JAR file into the container
COPY target/mysql-connector-java-8.0.27.jar /app

# Set the metadata label for the Docker image
LABEL maintainer="Jesús Ariel González Bonilla <jesusarielgb@gmail.com>" \
      app_name="Test Neoris" \
      version="0.1"

# Set environment variables
ENV ENV SPRING_DATASOURCE_URL=jdbc:mysql://172.17.0.2/test_fullstack \
    SPRING_DATASOURCE_USERNAME=root \
    SPRING_DATASOURCE_PASSWORD=abcd

# Expose port
EXPOSE 9000

# Start the application
CMD /bin/sh -c "until mysql -uroot -pabcd -h mysql-db -e 'SELECT 1'; do sleep 5; done; mysql -uroot -pabcd -h mysql-db -e 'CREATE DATABASE IF NOT EXISTS test_fullstack;' && java -Dserver.servlet.context-path=/neoris -Dserver.port=9000 -jar app.jar"