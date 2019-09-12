# Stackdriver Trace Test

## Runing
    
    npm run start 

Or

    node app.js 


## Docker

### Build image

    docker build -t="sd-trace" .
    
### Run image

    docker run -d -p 8080:8080 --name sd-trace sd-trace

### Pushing image

    docker images
    docker tag 7260caa01010 aolarte/sd-trace:0.0.1
    docker push aolarte/sd-trace:0.0.1

