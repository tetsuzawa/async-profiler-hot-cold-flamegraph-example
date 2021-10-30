FROM hseeberger/scala-sbt:8u282_1.5.5_2.11.12

RUN wget -O - https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.0/async-profiler-2.0-linux-x64.tar.gz | tar zxvf - && \
    mv async-profiler-2.0-linux-x64 /var/lib/async-profiler

WORKDIR /app

COPY . .

CMD ["sbt", "tomcat:start", "tomcat:join"]
