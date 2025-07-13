
FROM jenkins/jenkins:lts

USER root

RUN apt-get update \
    && apt-get install -y python3 python3-pip \
    && rm -rf /var/lib/apt/lists/*

RUN echo "2.0" > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY disable_wizard.groovy /usr/share/jenkins/ref/init.groovy.d/disable_wizard.groovy

USER jenkins

