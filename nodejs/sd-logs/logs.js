async function quickstart (
  projectId = 'andresolarte-tr', // Your Google Cloud Platform project ID
  logName = 'my-log' // The name of the log to write to
) {
  // Imports the Google Cloud client library
  const { Logging } = require('@google-cloud/logging')

  // Creates a client
  const logging = new Logging({ projectId })

  // Selects the log to write to
  const log = logging.log(logName)

  // The data to write to the log
  const text = 'Hello, world!'

  // The metadata associated with the entry
  const metadata = {
    // resource: {type: 'global'},
    resource: {
      labels: {
        module_id: 'default',
        project_id: 'andresolarte-tr',
        version_id: '20190910t112357',
        zone: 'us14'
      },
      type: 'gae_app'
    }
  }

  // Prepares a log entry
  const entry = log.entry(metadata, text)

  // Writes the log entry
  await log.write(entry)
  console.log(`Logged: ${text}`)
}

quickstart()
