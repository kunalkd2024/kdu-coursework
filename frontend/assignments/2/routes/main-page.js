const express = require('express');
const router = express.Router();
const posts = require('../data/posts')

// router.get('/posts', (req, res) => {
//     console.log('Request received.');
//     res.status(200).json({ redirectUrl: '/twitter2/public/main-page/main.html' });
// });

router.get('/posts', (req, res) => {
    const { page, pageSize } = req.query;
    const startIndex = (parseInt(page) - 1) * parseInt(pageSize);
    const endIndex = startIndex + parseInt(pageSize);
    const paginatedPosts = posts.slice(startIndex, endIndex);
    res.json(paginatedPosts);
});

router.get('/chats', (req, res) => {
    console.log('Request received.');
    res.status(200).json({ redirectUrl: '/twitter2/public/chat/chat.html' });
});

module.exports = router;