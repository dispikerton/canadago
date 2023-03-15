# Используйте Ubuntu образ для сборки
FROM ubuntu:22.04 AS build

# Установите необходимые пакеты
RUN apt-get update && \
    apt-get install -y wget unzip openjdk-17-jdk

# Установите Gradle
ARG GRADLE_VERSION=7.6.1
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle-${GRADLE_VERSION}-bin.zip && \
    mv gradle-${GRADLE_VERSION} /opt/gradle && \
    rm gradle-${GRADLE_VERSION}-bin.zip

# Добавьте Gradle в переменную среды PATH
ENV PATH="/opt/gradle/bin:${PATH}"

# Копируйте все файлы проекта в контейнер
COPY . /app
WORKDIR /app

# Соберите проект в контейнере
RUN gradle build --no-daemon

# Используйте Ubuntu образ для запуска
FROM ubuntu:22.04

# Установите OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jre-headless

# Копируйте собранный JAR-файл из стадии сборки в стадию запуска
COPY --from=build /app/build/libs/*.jar /app/app.jar


# Запустите приложение
ENTRYPOINT ["java", "-jar", "/app/app.jar"]