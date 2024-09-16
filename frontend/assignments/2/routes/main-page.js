const express = require("express");
const router = express.Router();
const posts = require("../data/posts");

router.get("/posts", (req, res) => {
  const { page, pageSize } = req.query;
  const startIndex = (parseInt(page) - 1) * parseInt(pageSize);
  const endIndex = startIndex + parseInt(pageSize);
  const paginatedPosts = posts.slice(startIndex, endIndex);
  res.json(paginatedPosts);
});

router.post("/posts", (req, res) => {
  const { name, username, content } = req.body;

  if (!name || !username || !content) {
    return res
      .status(400)
      .json({ error: "Name, username, and content are required." });
  }

  const newPost = {
    id: `post${posts.length + 1}`,
    name,
    username,
    content,
    timestamp: new Date().toISOString(),
  };

  posts.unshift(newPost);

  res.status(201).json(newPost);
});

router.get("/post", (req, res) => {
  const postId = req.query.id;

  const post = posts.find((post) => post.id === postId);

  if (!post) {
    return res.status(404).json({ error: "Post not found." });
  }

  res.json(post);
});

router.get("/chats", (req, res) => {
  console.log("Request received.");
  res.status(200).json({ redirectUrl: "/twitter2/public/chat/chat.html" });
});

module.exports = router;
