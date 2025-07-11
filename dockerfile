FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y python3 apt-transport-https ca-certificates curl

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

USER jenkins

