FROM java:8
EXPOSE 8734
VOLUME /tmp
ADD cosmos-shiro.jar shiro-docker.jar
RUN sh -c 'touch /shiro-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /shiro-docker.jar" ]