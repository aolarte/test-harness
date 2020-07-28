# HTTP Mock

An easy to configure HTTP server to help mock responses.

## Setup

Install node modules:

    npm install

## Runing
    
    npm run start -- --redirect=/:/admin --text=/admin:hello_admin --proxy=/google:http://www.google.com

    npm run start -- --redirect=/:/admin --text=/admin:hello_admin --delay=/admin:1000 --proxy=/proxy:http://localhost:8080/admin

    npm run start -- --text=/hello_good:hello_good --text=/hello_bad:hello_bad --error-rate=/hello_good:0 --error-rate=/hello_bad:100

Or

    node app.js --redirect=/:/admin --text=/admin:hello_admin --proxy=/google:http://www.google.com

### Options

* `--text=/hello:hello_world` Listen on `/hello/` and return a 200 with text `hello_world`
* `--redirect=/:/admin` Listen on `/` and return a 302 redirect to `/admin`
* `--proxy=/google:http://www.google.com` Listen on `/google` and display the raw HTML fetched from `http://www.google.com`, prefixed by `OK =>` or `BAD =>`
* `--error=/fail:bad` Listen on `/fail` and return a 503 error with text `bad`
* `--delay=/hello:1000` Delay responses to `/hello/` by 1 second.
* `--error-rate=/hello:30` Return an error (500) 30% of the times `/hello/` is invoked.

## Developing

### Testing

Run tests with:

    npm test

### Code quality

For Code Qualiaty, `standard` is configured:

    npm run standard
    npm run standard -- --fix


## Docker

### Build image

    docker build -t="http-mock" .
    
### Run image

    docker run -d -p 8080:8080 --name http-mock http-mock

### Pushing image

    docker images
    docker tag 7260caa01010 aolarte/http-mock:0.0.1
    docker push aolarte/http-mock:0.0.1

## Kubernetes

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mock-deployment
  labels:
    app: mock
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mock
  template:
    metadata:
      labels:
        app: mock
    spec:
      containers:
      - name: mock
        image: aolarte/http-mock:0.0.2
        ports:
        - containerPort: 8080
        command: ["nodejs"]
        args: 
        - "app.js"
        - "--text=/:ok_mock"
---
apiVersion: v1
kind: Service
metadata:
  name: mock
spec:
  selector:
    app: mock
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```

### Shell into the pod

    kubectl exec -it POD_NAME -- /bin/bash 
