FROM openjdk:8

LABEL vendor = "security-demo"

COPY . /home/security-demo

WORKDIR /home/security-demo

ADD target/security-demo.jar /home/security-demo/target/security-demo.jar

ENTRYPOINT ["java", "-jar", "-DMYSQL_HOST=120.79.222.247", "-DDATABASE_NAME=auth_demo", "-DMYSQL_USERNAME=worker", "-DREDIS_HOST=119.3.149.112", "/home/security-demo/target/security-demo.jar"]