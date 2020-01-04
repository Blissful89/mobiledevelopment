const express = require('express')
const bodyParser = require('body-parser')
const http = require('http')
const cors = require('cors')
const app = express()

const port = 1337

app.use(cors())
app.use(bodyParser())
app.use('/api',require('./route'))

http.createServer(app).listen(port, () => console.log(`Server started on http port: ${port}`))
