const ErrorResponse = require('../util/errorResponse');
const asyncHandler = require('../util/async');
const Food = require('../model/food');
const User = require('../model/user');

const selectFood = {
    name: 1,
    image: 1,
    description: 1,
    rate: 1,
    style: 1,
    types: 1,
    steps: 1
}
const selectType = {
    image: 1,
    description: 1,
    name: 1,
    ingredients: 1
}

const selectUser = {
    firstName: 1,
    lastName: 1,
    avatar: 1,
    createdAt: 1,
}

module.exports.postFood = asyncHandler(async function (req, res, next) {
    const foodInfo = {
        name,
        image,
        description,
        style,
        steps,
        types,
        comments
    } = req.body;

    const food = await Food.create(foodInfo);

    if (!food) {
        return next("Error create food", 401);
    }

    res
        .status(201)
        .json({
            success: true,
            data: food
        });

});

module.exports.getAllFood = asyncHandler(async function (req, res, next) {
    const foods = await Food.find().select(selectFood).populate({
        path: "types",
        select: selectType,
        populate: {
            path: "ingredients"
        }
    });

    res
        .status(200)
        .json({
            success: true,
            data: foods
        });
});

module.exports.getFoodById = asyncHandler(async function (req, res, next) {
    const foodId = req.params.foodId;

    const foods = await Food.findById(foodId).select(selectFood).populate({
        path: "types",
        select: selectType,
        populate: {
            path: "ingredients"
        }
    });

    res
        .status(200)
        .json({
            success: true,
            data: foods
        });

});

module.exports.getAllCommunity = asyncHandler(async function (req, res, next) {
    const selectFoodCommunity = {
        image: 1,
        description: 1,
        rate: 1,
        comments: 1
    }

    const foods = await Food.find().select(selectFoodCommunity).populate({
        path: "comments",
        populate: {
            path: "user"
        }
    });

    res
        .status(200)
        .json({
            success: true,
            data: foods
        });
});

module.exports.postComment = asyncHandler(async function (req, res, next) {
    const {
        userId,
        foodId,
        content
    } = req.body

    const food = await Food.findById(foodId).select({ comments: 1 });
    food.comments.push({ user: userId, content: content });

    await Food.findByIdAndUpdate(foodId, food);

    const comment = {
        user: await User.findById(userId),
        content: content
    }

    res
        .status(200)
        .json({
            success: true,
            data: comment
        });

});