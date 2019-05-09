FROM java:8
MAINTAINER 70kg 101@qq.com
VOLUME /tmp
ADD config-Server-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /eureka-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
