# Użyj oficjalnego obrazu JDK 21 jako bazowego
FROM eclipse-temurin:21-jdk

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj pliki projektu do obrazu
COPY . .

# Zbuduj aplikację przy użyciu Gradle
RUN ./gradlew build --no-daemon

# Ustaw port, na którym działa aplikacja Spring Boot
EXPOSE 8080

# Uruchom aplikację Spring Boot
CMD ["java", "-jar", "build/libs/task-manager-1.jar"]
