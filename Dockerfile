FROM adoptopenjdk/openjdk11

LABEL maintainer="Arpit Gupta <gupta.arpit03@gmail.com>"

COPY build/BulkMailing.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "BulkMailing.jar"]