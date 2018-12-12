
# SpringBoot app

Simple SpringBoot app that can be containerized.

# Containerization

## Build image with Docker

Uses `Dockerfile`.

    docker build -t="springboot-test" .

## Build image with Jib

    mvn compile jib:build

## Run image in Docker

    docker run -d -p 8080:8080 --name springboot-test springboot-test
    
## Push image to DockerHub

    docker tag 749896a4e2fe aolarte/springboot-test:latest
    docker login
    docker push aolarte/springboot-test

## Run image in GKE

    kubectl run boot --image=gcr.io/andresolarte-gke-ent/springboot

    kubectl expose deployment boot --type=NodePort --port 8080