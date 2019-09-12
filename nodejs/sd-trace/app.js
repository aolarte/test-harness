const tracer = require('@google-cloud/trace-agent').start({samplingRate: 100});


const express = require('express')
const rp = require("request-promise-native");

const version = '0.0.1'
const tag = process.env.TAG || 'No Tag'
const port = process.env.PORT || 8080



const app = express()

app.use(function (req, res, next) {
  console.log('Headers:', req.headers)
  next()
})

app.get('/hello', function(req, res) {
  console.log("Got a hello request.");
  res.send('Hello World!')
})

app.get('/fetch', function(req, res) {
  console.log("Got a fetch request.");
  rp(`http://localhost:${port}/hello`)
    .then(function(parsedBody) {
      console.log("Got a fetch response.");
      res.send(`OK => ${parsedBody}`);
    })
    .catch(function(err) {
      console.log("Failed to get a fetch response.", err);
      res.status(500);
      res.send(`Error`);
    });
})


app.listen(port, (err) => {
  if (err) {
    return console.log('Something bad happened', err)
  }
  console.log(`Server is listening on ${port}. Version: ${version} Tag: ${tag} `)
})
