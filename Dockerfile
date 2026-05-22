FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn -f service-registry/pom.xml clean package -DskipTests
RUN mvn -f api-gateway/pom.xml clean package -DskipTests
RUN mvn -f banking-service/pom.xml clean package -DskipTests
RUN mvn -f fund-transfer-service/pom.xml clean package -DskipTests
RUN mvn -f user-service/pom.xml clean package -DskipTests
RUN mvn -f utility-service/pom.xml clean package -DskipTests

CMD ["bash"]