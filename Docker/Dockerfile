FROM openjdk:11
LABEL author="Sergio Luigi" email="sergio.luigialves@hotmail.com"
COPY build/libs/*.jar /usr/src/groceries-manager.jar
WORKDIR /usr/src/
EXPOSE 8080
ENTRYPOINT ["java","-jar","groceries-manager.jar"]