/* eslint-env mocha */
const { spawn } = require('child_process')
const assert = require('assert')
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

  npm.stdout.on('data', (data) => {
    console.log(`npm stdout: ${data}`)
  })

  do {
    ready = await test(port)
  } while (!ready)

  return npm
}

describe('App', function () {
  var npm
  const port = 8081
  const args = ['--text=/hello:hello',
    '--redirect=/redirect:/new',
  `--proxy=/proxy:http://localhost:${port}/hello`,
  `--proxy=/invalidProxy:http://localhost:${port}/error`,
  '--error=/error:server_down'
  ]

  before(async function () {
    this.timeout(10000)
    npm = await startServer(port, args)
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

    it('hit a redirect', async function () {
      const response = await fetch(`http://localhost:${port}/redirect`, { redirect: 'manual' })
      assert.strictEqual(response.status, 302)
      assert.strictEqual(response.headers.get('location'), `http://localhost:${port}/new`)
    })

    it('hit a proxy', async function () {
      const response = await fetch(`http://localhost:${port}/proxy`)
      assert.strictEqual(response.ok, true)
      const body = await response.text()
      assert.strictEqual(body, 'OK => hello')
    })

    it('hit an invalid proxy', async function () {
      const response = await fetch(`http://localhost:${port}/invalidProxy`)
      assert.strictEqual(response.ok, true)
      const body = await response.text()
      assert.strictEqual(body, 'BAD => server_down')
    })

    it('hit a server error', async function () {
      const response = await fetch(`http://localhost:${port}/error`)
      assert.strictEqual(response.ok, false)
      const body = await response.text()
      assert.strictEqual(body, 'server_down')
    })
  })

  after(function () {
    npm.kill(9)
  })
})
