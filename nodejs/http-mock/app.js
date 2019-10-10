const express = require('express')

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


const data = new Map();

function fetchOrCreate(path) {
  if (data.has(path)) {
    return data.get(path)
  }
  pathData = {}
  data.set(path, pathData)
  return pathData
}

function processRequest(pathData, req, res) {
  if (pathData.code === 200) {
    res.send(pathData.text)
  } else if (pathData.code === 302) {
    res.redirect(pathData.redirect)
  }
}

function processGet(path, req, res) {
  const pathData = data.get(path)
  setTimeout(processRequest(pathData, req, res), (pathData.delay || 0))
  
}

if ('text' in argv) {
  toArray(argv.text).forEach(value => {
    const parts = value.split(':')

    pathData = fetchOrCreate(parts[0])
    pathData.code= 200
    pathData.text=parts[1]
    
    console.info(`Text route: ${value}`)
  })
}

if ('redirect' in argv) {
  toArray(argv.redirect).forEach(value => {
    const parts = value.split(':')

    pathData = fetchOrCreate(parts[0])
    pathData.code= 302
    pathData.redirect=parts[1]   

    console.info(`Redirect route: ${value}`)
  })
}


data.forEach((value, key) => {
  console.info(`Registering route: ${key}`)
  router.get(key, function (req, res) {
    processGet(key,req,res)
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
