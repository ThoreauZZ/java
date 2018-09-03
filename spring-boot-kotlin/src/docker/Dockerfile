FROM openjdk:8u131-jre-alpine

MAINTAINER zz.thoreau

#ADD @project.build.finalName@.jar @project.build.finalName@.jar
#ADD ${project.group}/${bootJar.baseName} ${bootJar.baseName}
ADD build/libs/spring-boot-kotlin-0.1.0.jar spring-boot-kotlin-0.1.0.jar
RUN sh -c 'touch /@project.build.finalName@.jar'

ENV JAVA_OPTS=""

CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /@project.build.finalName@.jar
