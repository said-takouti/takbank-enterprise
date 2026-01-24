FROM jenkins/jenkins:lts-jdk21

USER root

RUN curl -fsSLO https://download.docker.com/linux/static/stable/x86_64/docker-27.3.1.tgz \
  && tar xzvf docker-27.3.1.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-27.3.1.tgz
