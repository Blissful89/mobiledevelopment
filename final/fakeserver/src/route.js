const router = require('express').Router()

router.use('/tasks', require('./routes/tasks.js'))
router.use('/auth',require('./routes/auth.js'))

module.exports = router