const mongoose = require("mongoose");
const ObjectId = mongoose.Schema.Types.ObjectId;

const FoodSchema = new mongoose.Schema(
    {
        name: {
            type: String,
            require: [true, "Name food is required"]
        },
        image: {
            type: String,
            trim: true,
            default: "food_default.png",
        },
        description: {
            type: String,
            trim: true,
        },
        style: {
            type: String,
            required: [true, "Food type is required"],
            trim: true,
        },
        rate: {
            type: Number,
            default: 0.0
        },
        types: [
            {
                image: {
                    type: String,
                },
                description: {
                    type: String,
                    trim: true,
                },
                name: {
                    type: String,
                    required: [true, "Type name is required"]
                },
                ingredients: [
                    {
                        type: ObjectId,
                        ref: "Ingredient"
                    }
                ]
            }
        ],
        steps: [
            {
                type: String
            }
        ],
        comments: [
            {
                user: {
                    type: ObjectId,
                    ref: "User"
                },
                content: {
                    type: String,
                }
            }
        ]
    },
    {
        toObject: {
            virtuals: true,
        },
        toJSON: {
            virtuals: true,
        },
        timestamps: true
    }
);

FoodSchema.methods.toJSON = function() {
    return {
        foodId: this._id,
        name: this.name,
        image: this.image,
        description: this.description,
        style: this.style,
        rate: this.rate,
        steps: this.steps,
        types: this.types,
        comments: this.comments
    }
}

module.exports = mongoose.model("Food", FoodSchema);