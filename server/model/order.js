const mongoose = require("mongoose");
const ObjectId = mongoose.Schema.Types.ObjectId;

const OrderSchema = new mongoose.Schema(
    {
        userId: {
            type: ObjectId,
            ref: "User"
        },
        ingredients: [
            {
                amount: {
                    type: Number,
                    default: 0
                },
                ingredient: {
                    type: ObjectId,
                    ref: "Ingredient"
                }
            }
        ],
        totalPrice: {
            type: Number,
            default: 0.0
        },
        delivery: {
            type: Number,
            default: 0.0
        },
        promotion: {
            type: Number,
            default: 0.0
        },
        taxes: {
            type: Number,
            default: 0.0
        },
        isActive: {
            type: Boolean,
            default: true
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
    }
);

OrderSchema.methods.toJSON = function () {
    return {
        orderId: this._id,
        totalPrice: this.totalPrice,
        delivery: this.delivery,
        promotion: this.promotion,
        taxes: this.taxes,
        ingredients: this.ingredients
    }
}

module.exports = mongoose.model("Order", OrderSchema);