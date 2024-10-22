FROM ubuntu:22.04
RUN apt-get update && apt-get -y upgrade
RUN apt-get install openjdk-17-jre curl -y
SHELL ["/bin/bash", "--login", "-i", "-c"]
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash
RUN source /root/.bashrc && nvm install 22
SHELL ["/bin/bash", "--login", "-c"]
COPY . /usr/home/statsify
WORKDIR /usr/home/statsify/build/libs
ENTRYPOINT ["java", "-jar", "fat.jar"]