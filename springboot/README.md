

# Docker

## Build image

    docker build -t="springboot-test" .
    
## Run image

    docker run -d -p 8080:8080 --name springboot-test springboot-test
    
## Push image to DockerHub

    docker tag 749896a4e2fe aolarte/springboot-test:latest
    docker login
    docker push aolarte/springboot-test
