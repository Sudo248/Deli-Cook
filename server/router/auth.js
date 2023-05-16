const express = require('express');
const router = express.Router();

const{
    register,
    login,
    changePassword
} = require('../controller/user');

router.post('/register', register);

router.post('/login', login);

router.put('/reset-pass', changePassword);

module.exports = router;