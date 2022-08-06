FROM clojure

COPY target/hello-world-0.1.0-SNAPSHOT.jar hello-world-0.1.0-SNAPSHOT.jar

ENV PORT 8080

EXPOSE 8080

CMD ["java", "-jar", "hello-world-0.1.0-SNAPSHOT.jar"]
