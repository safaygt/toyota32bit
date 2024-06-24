<h1 align="center">TOYOTA32BİT BACKEND Project</h1>

Toyota32Bit Backend Project is a backend project which manages databases, services, and applications by using API and Docker. 

This is a [*SpringBoot Project*](https://spring.io/projects/spring-boot) with using [*Maven*](https://maven.apache.org/).

# Getting Started


## Step 1: Start the Metro Server


First you will need to start Docker-Desktop, after that you need to go to directory which includes pom.xml

To install dependencies you need to write 

bash
# using mvn
mvn clean package

Then you need to go to directory which includes Dockerfile. You will type to bash

# Docker
docker build -t "any name you want".


## Step 2: Start your Application

I am using Intellij Idea so, if you use Intellij Idea as well, just click run button. 

## Step 3: Check Your Logs

If you have some problems while you are starting the program, you can check the errors, debugs, warns from logging. It will help you to solve problems. 

## Congratulations! :tada:

You've successfully run and fixed your Spring Boot App. :partying_face:

# Learn More

To learn more about Spring Boot Project, take a look at the following resources:

- [Spring Boot Website](https://spring.io/projects/spring-boot) - learn more about Spring Boot.
- [Docker Web Site](https://docs.docker.com/get-docker/) - learn more about Docker.
- [Maven Web Site](https://maven.apache.org/guides/) - learn more about Maven

