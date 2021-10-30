FROM hseeberger/scala-sbt:8u282_1.5.5_2.11.12

WORKDIR /app

COPY . .

CMD ["sbt", "tomcat:start", "tomcat:join"]
