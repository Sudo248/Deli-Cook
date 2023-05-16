const express = require("express");
const dotenv = require("dotenv");
const cors = require("cors");
const connectDB = require("./config/database");
const errorHandler = require('./util/error');
const bodyParser = require('body-parser');
const auth = require('./router/auth');
const user = require('./router/user');
const food = require('./router/food');
const ingredient = require('./router/ingredient');
const order = require('./router/order');

const image = require('./router/image');
const { protect } = require("./middlewares/auth");

dotenv.config({path: ".env"});

connectDB();

const app = express();

app.use("*", cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

const api_url = "/api/v1";

app.use(`${api_url}/auth`, auth);

app.use('/',express.static("public"));

app.use(`${api_url}/image`, image);

app.use(protect)

app.use(`${api_url}/user`, user);
app.use(`${api_url}/food`, food);
app.use(`${api_url}/ingredient`, ingredient);
app.use(`${api_url}/order`, order);

app.get(`/`,(req, res)=>{
    res.sendFile(__dirname + '/public/index.html');
});

app.use(errorHandler);

const PORT = process.env.PORT || 5000;

const server = app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});