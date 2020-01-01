const router = require("express").Router();
let items = require('../fakeitems/items')
const sortItems = require('../fakeitems/sort')

// Read
router.get("/", (_, res) => {
    res.status(200).send(sortItems(items));
});

// Create / Update
router.post('/', (req,res) => {
    items = items.filter((item) => req.body.title !== item.title)
    items.push(req.body)
    res.status(200).send(sortItems(items))
})

// Delete
router.delete('/', (req,res) => {
    items = items.filter((item) => req.body.title !== item.title)
    res.status(200).send(sortItems(items))
})

module.exports = router;
