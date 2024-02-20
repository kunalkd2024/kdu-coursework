const users = require("../data/users");
const express = require("express");
const router = express.Router();

router.get("/", (req, res) => {
  console.log("Request received.");
  res.status(200).json({ redirectUrl: "/twitter2/public/main-page/main.html" });
});
