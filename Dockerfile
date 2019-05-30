FROM mavenqa.got.volvo.net:18443/openjdk:8

WORKDIR /springboot

ADD toDoService-0.0.1-SNAPSHOT.jar toDoService-0.0.1-SNAPSHOT.jar

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "toDoService-0.0.1-SNAPSHOT.jar"] 