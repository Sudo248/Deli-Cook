const express = require('express');

const router = express.Router();

const {
    getOrder,
    postOrder,
    putOrder,
    deleteOrderIngredient,
    getActiveOrder,
    payOrder
} = require('../controller/order');


router.post('/', postOrder);

router.route('/active')
    .get(getActiveOrder)

router.route('/:orderId')
    .get(getOrder)
    .put(putOrder);

router.route('/pay/:orderId')
    .put(payOrder);

router.route('/:orderId/delete/:ingredientId')
    .delete(deleteOrderIngredient);

module.exports = router;