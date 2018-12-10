
# SpringBoot app

Simple SpringBoot app that can be containerized.

# Containerization

## Build image with Docker

Uses `Dockerfile`.

    docker build -t="springboot-test" .

## Build image with Jib

    mvn compile jib:build

## Run image

    docker run -d -p 8080:8080 --name springboot-test springboot-test
    
## Push image to DockerHub

    docker tag 749896a4e2fe aolarte/springboot-test:latest
    docker login
    docker push aolarte/springboot-test
