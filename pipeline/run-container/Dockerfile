FROM alpine
RUN apk add openjdk11
ENV PATH=$PATH:/usr/lib/jvm/java-17-openjdk/bin/
WORKDIR /usr/javaCode
ADD Test.java Test.java
ENTRYPOINT javac Test.java && java Test.java $number