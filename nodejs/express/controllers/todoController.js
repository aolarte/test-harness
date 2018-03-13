'use strict'

var todos = {} // Global variable

exports.list_all_tasks = function (req, res) {
  res.json(todos)
}

exports.create_a_task = function (req, res) {
  // Apply different logic based on content type
  if (req.headers['content-type'] === 'application/json') {
    todos[req.body.taskId] = req.body
    console.log('Task successfully created')
    res.json({ message: 'Task successfully created' })
  } else {
    console.log('We only accept application/json')
    res.status(406)
    res.send('We only accept application/json')
  }
}

exports.read_a_task = function (req, res) {
  var todo = todos[req.params.taskId]

  //  if (err) { res.send(err) }
  res.json(todo)
}

exports.update_a_task = function (req, res) {
  todos[req.body.taskId] = req.body
}

exports.delete_a_task = function (req, res) {
  delete todos[req.body.taskId]
}
