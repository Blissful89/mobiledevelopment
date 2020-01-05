const router = require("express").Router();
const account = require('../fakeitems/credentials')

// Read
router.get("/", (req, res) => {
    if (isValid(req)) {
        console.log("VALID LOGIN")
        res.status(200).send({ status: "ok", account: { name: account.name } })
    }
    else {
        console.log("INVALID LOGIN")
        res.status(401).send({ status: "Unauthorized" })
    }
});

const isValid = (req) => req.query.name === account.name && req.query.password === account.password

module.exports = router;
