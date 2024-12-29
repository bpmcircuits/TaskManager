# Użyj oficjalnego obrazu JDK 21 jako bazowego
FROM eclipse-temurin:21-jdk

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj pliki projektu do obrazu
COPY . .

# Zbuduj aplikację przy użyciu Gradle
RUN ./gradlew build --no-daemon

# Ustaw port aplikacji (Render wymaga zmiennej środowiskowej PORT)
ENV PORT=8080

# Eksponuj port (Render automatycznie przekierowuje na port HTTPS, nie używaj :8080 w URL)
EXPOSE 8080

# Uruchom aplikację Spring Boot
CMD ["sh", "-c", "java -jar build/libs/task-manager-1.jar --server.port=$PORT"]

