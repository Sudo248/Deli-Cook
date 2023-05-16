const ErrorResponse = require('../util/errorResponse');
const asyncHandler = require('../util/async');
const Order = require('../model/order');
const Ingredient = require('../model/ingredient');

module.exports.getOrder = asyncHandler(async function (req, res, next) {
    const orderId = req.params.orderId;
    const order = await Order.findById(orderId).populate({
        path: "ingredients",
        populate: "ingredient"
    })

    res
        .status(201)
        .json({
            success: true,
            data: order
        });
})

module.exports.postOrder = asyncHandler(async function (req, res, next) {
    const orderInfo = {
        userId,
        ingredients
    } = req.body;

    let totalPrice = 0

    for (let index = 0; index < ingredients.length; index++) {
        const element = ingredients[index];
        totalPrice += element.ingredient.price * element.amount
        ingredients[index].ingredient = element.ingredient.ingredientId
    }

    console.log(ingredients)

    const delivery = 2
    const promotion = 0
    const taxes = 1
    totalPrice += 3

    const order = await Order.create({
        userId,
        ingredients,
        totalPrice,
        delivery,
        promotion,
        taxes
    });

    if (!order) {
        return next("Error create order", 401);
    }

    const newOrder = await Order.findById(order._id).populate({
        path: "ingredients",
        populate: "ingredient"
    })

    res
        .status(201)
        .json({
            success: true,
            data: newOrder
        });

});

module.exports.putOrder = asyncHandler(async function (req, res, next) {
    const orderId = req.params.orderId;

    const {
        amount,
        ingredient
    } = req.body;

    const order = await Order.findById(orderId);

    if (!order) {
        return next("Order invalid", 401);
    }

    for (let index = 0; index < order.ingredients.length; index++) {
        const element = order.ingredients[index];
        if (ingredient.ingredientId == element.ingredient) {
            const _ingredient = await Ingredient.findById(ingredient.ingredientId)
            order.totalPrice += (amount - order.ingredients[index].amount) * _ingredient.price
            order.ingredients[index].amount = amount
            break;
        }
    }

    await Order.findByIdAndUpdate(orderId, order);

    const newOrder = await Order.findById(order._id).populate({
        path: "ingredients",
        populate: "ingredient"
    })

    res
        .status(201)
        .json({
            success: true,
            data: newOrder
        });

});

module.exports.deleteOrderIngredient = asyncHandler(async function (req, res, next) {
    const {
        orderId,
        ingredientId
    } = req.params;

    const order = await Order.findById(orderId);

    if (!order) {
        return next("Order invalid", 401);
    }

    let deleteIndex = 0

    for (let index = 0; index < order.ingredients.length; index++) {
        const element = order.ingredients[index];
        if (ingredientId == element.ingredient) {
            const _ingredient = await Ingredient.findById(ingredientId)
            order.totalPrice -= order.ingredients[index].amount * _ingredient.price
            deleteIndex = index
            break;
        }
    }

    order.ingredients.splice(deleteIndex, 1)

    await Order.findByIdAndUpdate(orderId, order);

    const newOrder = await Order.findById(order._id).populate({
        path: "ingredients",
        populate: "ingredient"
    })

    res
        .status(201)
        .json({
            success: true,
            data: newOrder
        });

});

module.exports.payOrder = asyncHandler(async function (req, res, next) {
    const orderId = req.params.orderId

    await Order.findByIdAndUpdate(orderId, { isActive: false })

    res
        .status(201)
        .json({
            success: true,
            data: "Pay success"
        });

});

module.exports.getActiveOrder = asyncHandler(async function (req, res, next) {
    const userId = req.body.userId
    console.log("userId: ", userId)
    const order = await Order.findOne({userId: userId, isActive: true}).populate({
        path: "ingredients",
        populate: "ingredient"
    });

    if (!order) {
        return next(new ErrorResponse("Please create order before", 401));
    }

    res
        .status(201)
        .json({
            success: true,
            data: order
        });

});