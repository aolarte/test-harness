# HTTP Mock

An easy to configure HTTP server to help mock responses.

## Runing
    
    npm run start -- --redirect=/:/admin --text=/admin:hello_admin

Or

    node app.js --redirect=/:/admin --text=/admin:hello_admin


## Docker

### Build image

    docker build -t="http-mock" .
    
### Run image

    docker run -d -p 8080:8080 --name http-mock http-mock

### Pushing image

    docker images
    docker tag 7260caa01010 aolarte/http-mock:0.0.1
    docker push aolarte/http-mock:0.0.1

