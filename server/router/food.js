const express = require('express');

const router = express.Router();

const {
    postFood,
    getAllFood,
    getAllCommunity,
    postComment,
    getFoodById
} = require('../controller/food');

router
    .get('/', getAllFood)
    .get('/community', getAllCommunity)
    .get('/:foodId', getFoodById);

router
    .post('/',postFood)
    .post('/comment', postComment);

module.exports = router;