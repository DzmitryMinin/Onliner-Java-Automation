FROM  markhobson/maven-chrome:jdk-17

WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "install"]