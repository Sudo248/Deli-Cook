const express = require('express');

const router = express.Router();

const {
    getMe,
    changePassword
} = require('../controller/user');

router
.get('/me', getMe)
.patch('/change-pass', changePassword)
// .get('/hotels', getHotels);

module.exports = router;