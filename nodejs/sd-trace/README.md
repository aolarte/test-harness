# Stackdriver Trace Test

## Runing
    
    npm run start 

Or

    node app.js 

## Fudging with Trace Context

It's possible (but hacky) to modify the trace context:


    app.use((req, _, next) => {
      if (req.body.traceContext) {
        console.log('traceContext: ', req.body.traceContext)
        const parts = req.body.traceContext.split('/')
        const traceId = parts[0]
        const parentSpanId = parts[1].split(';')[0]
        const data = tracer.getCurrentRootSpan()
        data.trace.traceId = traceId
        data.span.parentSpanId = parentSpanId
      }  
      next();  
    });



## Docker

### Build image

    docker build -t="sd-trace" .
    
### Run image

    docker run -d -p 8080:8080 --name sd-trace sd-trace

### Pushing image

    docker images
    docker tag 7260caa01010 aolarte/sd-trace:0.0.1
    docker push aolarte/sd-trace:0.0.1

