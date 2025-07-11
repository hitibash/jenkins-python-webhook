FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y apt-transport-https python3 ca-certificates \
    && rm -rf /var/lib/apt/lists/*

USER jenkins

