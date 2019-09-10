const express = require('express')

const version = '0.0.1'
const tag = process.env.TAG || 'No Tag'
const port = process.env.PORT || 8080

const argv = require('minimist')(process.argv.slice(2))
console.log('Started with parameters:')
console.dir(argv)

const app = express()

const router = express.Router()

function toArray (value) {
  if (Array.isArray(value)) {
    return value
  }
  return [value]
}

if ('text' in argv) {
  toArray(argv.text).forEach(value => {
    const parts = value.split(':')
    router.get(parts[0], function (req, res) {
      res.send(parts[1])
    })
    console.info(`Text route: ${value}`)
  })
}

if ('redirect' in argv) {
  toArray(argv.redirect).forEach(value => {
    const parts = value.split(':')
    router.get(parts[0], function (req, res) {
      res.redirect(parts[1])
    })
    console.info(`Redirect route: ${value}`)
  })
}

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
