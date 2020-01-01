const router = require("express").Router();
let items = require('../fakeitems/items')
const sortItems = require('../fakeitems/sort')

// Read
router.get("/", (_, res) => {
    console.log("GET REQUEST RECEIVED")
    res.status(200).send({ results: sortItems(items) })
});

// Create / Update
router.post('/', (req, res) => {
    console.log("POST REQUEST RECEIVED")
    items = items.filter((item) => req.body.title !== item.title)
    items.push(req.body)
    res.status(200).send({ results: sortItems(items) })
})

// Delete
router.delete('/', (req, res) => {
    console.log("DELETE REQUEST RECEIVED")
    items = items.filter((item) => req.body.title !== item.title)
    res.status(200).send({ results: sortItems(items) })
})

module.exports = router;
