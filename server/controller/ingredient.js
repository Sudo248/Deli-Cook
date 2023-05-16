const ErrorResponse = require('../util/errorResponse');
const asyncHandler = require('../util/async');
const Ingredient = require('../model/ingredient');

module.exports.postIngredient = asyncHandler(async function(req, res, next){
    const ingredientInfo = {
        name,
        image,
        from,
        price,
        amount
    } = req.body;

    const ingredient = await Ingredient.create(ingredientInfo);

    if(!ingredient){
        return next("Error create ingredient", 401);
    }

    res
    .status(201)
    .json({
        success: true,
        data: ingredient
    });
});