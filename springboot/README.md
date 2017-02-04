

# Docker

## Build image

    docker build -t="springboot-test" .
    
## Run image

    docker run -d -p 8080:8080 --name springboot-test springboot-test