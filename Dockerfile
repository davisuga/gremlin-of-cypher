FROM clojure:lein

ENV PORT 8080

EXPOSE 8080

CMD ["lein", "ring", "server-headless"]
