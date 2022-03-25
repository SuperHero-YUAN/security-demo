FROM openjdk:8

LABEL vendor = "security-demo"

COPY . /home/security-demo

WORKDIR /home/security-demo

ADD target/security-demo.jar /home/security-demo/target/security-demo.jar

ENTRYPOINT ["java", "-jar", "/home/security-demo/target/security-demo.jar"]