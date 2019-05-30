FROM mavenqa.got.volvo.net:18443/openjdk:8

WORKDIR /springboot

ADD restful-web-services-0.0.1-SNAPSHOT.jar restful-web-services-0.0.1-SNAPSHOT.jar

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "restful-web-services-0.0.1-SNAPSHOT.jar"] 
