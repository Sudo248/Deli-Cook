const express = require('express');

const router = express.Router();

const {
    getMe
} = require('../controller/user');

router
.get('/me', getMe)

module.exports = router;