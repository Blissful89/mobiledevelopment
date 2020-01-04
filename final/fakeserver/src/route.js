const router = require('express').Router()

router.use('/tasks', require('./routes/tasks.js'))

module.exports = router