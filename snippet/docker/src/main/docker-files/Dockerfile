FROM ${base.image}
LABEL maven.group.id="${project.groupId}"
LABEL maven.artifact.id="${project.artifactId}" 
LABEL maven.version="${project.version}"
LABEL description="${project.description}"
LABEL scm="${project.scm.developerConnection}"
LABEL service.name="${service.name}"
LABEL service.suffix="${service.suffix}"
LABEL vendor="${project.organization.name}"

ENV TZ Europe/Lisbon

#ARG some envaroment variables used only at build time

RUN yum install -y java-1.8.0-openjdk-headless && \
    yum clean all && \
    rm -rf /var/cache/yum

#https://github.com/jwilder/dockerize
#dockerize is a util tool for docker applications
ENV DOCKERIZE_VERSION v0.6.0
RUN wget https://github.com/jwilder/dockerize/releases/download/v0.6.0/dockerize-alpine-linux-amd64-v0.6.0.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-v0.6.0.tar.gz \
    && rm dockerize-alpine-linux-amd64-v0.6.0.tar.gz

#copy from host to image
COPY docker_files /root
#change permissions to execute scripts
RUN  chmod 755 /root/*.sh

#expose a tcp port
EXPOSE 8080

#define a entrypoint script
ENTRYPOINT ["/root/entrypoint.sh"]

#excute some script before inicialization
CMD ["/root/run-cucumber-report-web.sh"]

LABEL build-timestamp="${maven.build.timestamp}"
RUN echo "Image created from ${base.image} on ${maven.build.timestamp}" >/README.txt
