const mongoose = require("mongoose");
const ObjectId = mongoose.Schema.Types.ObjectId;

const IngredientSchema = new mongoose.Schema(
    {
        name: {
            type: String,
            require: [true, "Name ingrdient is required"]
        },
        image: {
            type: String,
            trim: true,
            default: "default path image",
        },
        from: {
            type: String,
            default: ""
        },
        price: {
            type: Number,
        },
        amount: {
            type: Number,
            default: 1
        }
    },
    {
        toObject: {
            virtuals: true,
        },
        toJSON: {
            virtuals: true,
        },
        timestamps: true
    });


IngredientSchema.methods.toJSON = function () {
    return {
        ingredientId: this._id,
        name: this.name,
        image: this.image,
        from: this.from,
        price: this.price,
        amount: this.amount,
    }
}

module.exports = mongoose.model("Ingredient", IngredientSchema);



