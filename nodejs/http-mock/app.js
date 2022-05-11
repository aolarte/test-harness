if (process.env.SD_TRACE === 'true') {
  require('@google-cloud/trace-agent').start({
    serviceContext: {
      service: process.env.SERVICE_NAME || 'default service',
      version: process.env.SERVICE_VERSION || 'def'
    }
  })
}

if (process.env.SD_DEBUG === 'true') {
  require('@google-cloud/debug-agent').start({
    allowExpressions: true,
    serviceContext: {
      service: process.env.SERVICE_NAME || 'default service',
      version: process.env.SERVICE_VERSION || 'def'
    }
  })
}

const express = require('express')
const bodyParser = require('body-parser')
const promMid = require('express-prometheus-middleware')
const fetch = require('node-fetch')

const client = require('prom-client')
const counter = new client.Counter({
  name: 'custom_counter',
  help: 'Custom Counter Help text'
})
counter.inc() // Inc with 1
counter.inc(10) // Inc with 10

const version = '0.0.2'
const tag = process.env.TAG || 'No Tag'
const port = process.env.PORT || 8080

const argv = require('minimist')(process.argv.slice(2))
console.log('Started with parameters:')
console.dir(argv)

console.log('Started with env:')
console.dir(process.env)

const app = express()

const router = express.Router()

function toArray (value) {
  if (Array.isArray(value)) {
    return value
  }
  return [value]
}

const data = new Map()

function fetchOrCreate (path) {
  if (data.has(path)) {
    return data.get(path)
  }
  const pathData = {}
  data.set(path, pathData)
  return pathData
}

function splitArg (arg) {
  const path = arg.substring(0, arg.indexOf(':'))
  const value = arg.substring(arg.indexOf(':') + 1, arg.length)
  return [path, value]
}

function shouldThrowError (pathData) {
  if ((pathData.errorRate || 0) > 0) {
    const random = Math.random()

    if (pathData.errorRate > (random * 100)) {
      return true
    }
  }
  return false
}

function processRequest (pathData, req, res) {
  if (shouldThrowError(pathData)) {
    res.status(500).send('Server Error')
  } else if (pathData.proxy) {
    // res.send('Proxy')
    const proxyResp = fetch(pathData.proxy)
    var ok = false
    const proxyData = proxyResp.then(res => { ok = res.ok; return res.text() })
    proxyResp.catch(() => res.send('Bad response'))
    proxyData.then(body => {
      if (ok) {
        res.send(`OK => ${body}`)
      } else {
        res.send(`BAD => ${body}`)
      }
    })
    proxyData.catch(() => res.send('Bad data'))
  } else if (pathData.code === 200) {
    res.send(pathData.text)
  } else if (pathData.code === 302) {
    res.redirect(pathData.redirect)
  } else if (pathData.code === 503) {
    res.status(503).send(pathData.text)
  }
}

function processGet (path, req, res) {
  const pathData = data.get(path)
  setTimeout(() => processRequest(pathData, req, res), (pathData.delay || 0))
}

function processPatch (path, req, res) {
  if (data.has(path)) {
    const pathData = data.get(path)
    const updatedData = Object.assign(pathData, req.body)
    if (req.accepts('json')) {
      res.send(updatedData)
    } else {
      res.send('OK')
    }
  } else {
    res.status(404)
  }
}

if ('text' in argv) {
  toArray(argv.text).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.code = 200
    pathData.text = parts[1]
    console.info(`Text route: ${value}`)
  })
}

if ('redirect' in argv) {
  toArray(argv.redirect).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.code = 302
    pathData.redirect = parts[1]
    console.info(`Redirect route: ${value}`)
  })
}

if ('error' in argv) {
  toArray(argv.error).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.code = 503
    pathData.text = parts[1]
    console.info(`Error route: ${value}`)
  })
}

if ('proxy' in argv) {
  toArray(argv.proxy).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.proxy = parts[1]
    console.info(`Proxy route: ${value}`)
  })
}

if ('delay' in argv) {
  toArray(argv.delay).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.delay = parts[1]
    console.info(`Add delay for route: ${value}`)
  })
}

if ('error-rate' in argv) {
  toArray(argv['error-rate']).forEach(value => {
    const parts = splitArg(value)
    const pathData = fetchOrCreate(parts[0])
    pathData.errorRate = parts[1]
    console.info(`Add error rate for route: ${value}`)
  })
}

data.forEach((value, key) => {
  console.info(`Registering route: ${key}`)
  router.get(key, function (req, res) {
    processGet(key, req, res)
  })

  router.patch(key, function (req, res) {
    processPatch(key, req, res)
  })
})

app.disable('x-powered-by')
app.use(function (req, res, next) {
  res.header('X-Mock-Server', `HTTP Mock; ${version} / ${tag}`)
  next()
})
app.use(bodyParser.json())
app.use(promMid({
  metricsPath: '/metrics',
  collectDefaultMetrics: true,
  requestDurationBuckets: [0.1, 0.5, 1, 1.5]
}))
app.use('/', router)
app.listen(port, (err) => {
  if (err) {
    return console.log('Something bad happened', err)
  }
  console.log(`Server is listening on ${port}. Version: ${version} Tag: ${tag} `)
})
