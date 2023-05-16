const express = require('express');

const router = express.Router();

const {
    postIngredient
} = require('../controller/ingredient')

router.post('/',postIngredient);

module.exports = router;