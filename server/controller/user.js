const ErrorResponse = require('../util/errorResponse');
const asyncHandler = require('../util/async');
const User = require('../model/user');


const selectUser = {
    firstName: 1,
    lastName: 1,
    avatar: 1,
    createdAt: 1,
}

module.exports.register = asyncHandler(async function (req, res, next) {
    const userInfo = { email, password, name, avatar } = req.body;

    const user = await User.create(userInfo);

    if(!user){
        return next("Error register", 401);
    }

    res
    .status(201)
    .json({
        success: true,
        data: user
    });

});

module.exports.login = asyncHandler(async function (req, res, next) {
    const { email, password } = req.body;

    if (!email || !password) {
        return next(new ErrorResponse("Please provide an email and password", 400));
    }

    const user = await User.findByIdentifier(email);

    if (!user) {
        return next(new ErrorResponse("Invalid user", 400));
    }

    const isCorrectedPassword = await user.comparePassword(password);

    if (!isCorrectedPassword) {
        return next(new ErrorResponse("Wrong password", 400));
    }

    // Create token
    const token = await user.getSignedJWTToken();
    console.log(token)
    res.status(200).json({
        success: true,
        data: {token},
    });
});


module.exports.getMe = asyncHandler(async function (req, res, next) {
    const userId = req.body.userId;

    const user = await User.findById(userId);

    res
    .status(200)
    .json({
        success: true,
        data: user,
    });

});

module.exports.changePassword = asyncHandler(async function(req, res, next){
    const {
        email,
        newPassword
    } = req.body;

    const user = await User.findByIdentifier(email);
    user.password = newPassword;
    await user.save();

    res.status(200).json({
        success:true,
        data: user
    });
});