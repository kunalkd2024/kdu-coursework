const express = require("express");
const router = express.Router();
const users = require("../data/users");

router.post("/user/login", (req, res) => {
  const { username, password } = req.body;
  const user = users.find(
    (u) => u.user_name === username && u.password === password
  );
  if (!user) {
    return res.status(401).json({ message: "Invalid username or password" });
  }
  res.status(200).json({
    message: "Login successful",
    user,
    redirect_url: `/twitter2/public/main-page/main.html?name=${user.user_name}`,
  });
});

module.exports = router;
