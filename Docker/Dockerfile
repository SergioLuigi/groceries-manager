FROM adoptopenjdk/openjdk11
LABEL author="Sergio Luigi" email="sergio.luigialves@hotmail.com"
COPY build/libs/*.jar groceries-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/groceries-manager.jar"]