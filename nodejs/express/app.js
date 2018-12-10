const path = require('path')
const express = require('express')
const exphbs = require('express-handlebars')
const bodyParser = require('body-parser')

const port = 8080

const app = express()

app.engine('.hbs', exphbs({
  defaultLayout: 'main',
  extname: '.hbs',
  layoutsDir: path.join(__dirname, 'views/layouts')
}))
app.set('view engine', '.hbs')
app.set('views', path.join(__dirname, 'views'))

app.get('/', (request, response) => {
  response.render('home', {
    name: 'John Doe'
  })
})

const rest = express()

rest.use(bodyParser.urlencoded({ extended: true }))
rest.use(bodyParser.json())

var todoList = require('./controllers/todoController.js')

rest.route('/tasks')
  .get(todoList.list_all_tasks)
  .post(todoList.create_a_task)

rest.route('/tasks/:taskId')
  .get(todoList.read_a_task)
  .put(todoList.update_a_task)
  .delete(todoList.delete_a_task)

app.use('/rest', rest) // mount the sub app (REST webservices)

app.listen(port, (err) => {
  if (err) {
    return console.log('Something bad happened', err)
  }
  console.log(`Server is listening on ${port}`)
})
