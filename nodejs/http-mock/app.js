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
const fetch = require('node-fetch')

const version = '0.0.1'
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

function processRequest (pathData, req, res) {
  if (pathData.proxy) {
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

data.forEach((value, key) => {
  console.info(`Registering route: ${key}`)
  router.get(key, function (req, res) {
    processGet(key, req, res)
  })
})

app.disable('x-powered-by')
app.use(function (req, res, next) {
  res.header('X-Mock-Server', `HTTP Mock; ${version} / ${tag}`)
  next()
})
app.use('/', router)
app.listen(port, (err) => {
  if (err) {
    return console.log('Something bad happened', err)
  }
  console.log(`Server is listening on ${port}. Version: ${version} Tag: ${tag} `)
})
