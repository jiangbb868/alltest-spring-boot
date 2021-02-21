FROM alpine:3.10
ENV BASE_NAMESPACE "127.0.0.1"
ENV MYSQL_USER "root"
ENV MYSQL_PASSWORD "root"

ADD helm-1.0-SNAPSHOT.jar /opt/app/

RUN apk update \
    && apk add openjdk8 busybox tzdata curl \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtim \
    && echo Asia/Shanghai > /etc/timezone \
    && apk del tzdata \
    && rm -rf /tmp/* /var/cache/apk/*

CMD ["java", "-jar", "/opt/app/helm-1.0-SNAPSHOT.jar"]
