/* eslint-env mocha */
const { spawn } = require('child_process')
const assert = require('assert')
// const request = require('request')
// const rp = require('request-promise');
const fetch = require('node-fetch')

function sleep (ms) {
  return new Promise(resolve => setTimeout(resolve, ms))
}

async function test (port) {
  try {
    const response = await fetch(`http://localhost:${port}/hello`)
    if (response.ok) {
      return true
    }
  } catch (e) {
  }
  await sleep(10)
  return false
}

async function startServer (port, args) {
  const opts = { PORT: port }
  args.unshift('app.js')
  var npm = spawn('node', args, { env: { ...process.env, ...opts } })
  var ready = false
  do {
    ready = await test(port)
  } while (!ready)

  return npm
}

describe('App', function () {
  var npm
  const port = 8081

  before(async function () {
    this.timeout(10000)
    npm = await startServer(port, ['--text=/hello:hello', '--redirect=/redirect:/new'])
  })

  describe('Basic tests', function () {
    it('hit a get URL', async function () {
      const response = await fetch(`http://localhost:${port}/hello`)
      assert.strictEqual(response.ok, true)
      const body = await response.text()
      assert.strictEqual(body, 'hello')
    })

    it('hit an invalid URL', async function () {
      const response = await fetch(`http://localhost:${port}/notFound`)
      assert.strictEqual(response.status, 404)
    })

    it('hit an redirect', async function () {
      const response = await fetch(`http://localhost:${port}/redirect`, { redirect: 'manual' })
      assert.strictEqual(response.status, 302)
      assert.strictEqual(response.headers.get('location'), `http://localhost:${port}/new`)
    })
  })

  after(function () {
    // npm.stdout.on('data', (data) => {
    //     console.log(`stdout: ${data}`)
    // })

    npm.kill(9)
  })
})
