const socket = io("http://localhost:3000")

function sendMainPageLoadedMessage() {
    socket.emit('mainPageLoaded', { message: 'Main page loaded' });
}

window.onload = sendMainPageLoadedMessage();

const newPost = {
    username: 'Nitesh Gupta',
    handle: '@nit_hck',
    time: 'Just now'
};

function createNewPost(content) {
    const postsContainer = document.querySelector('.posts');
    const postId = "post" + (postsContainer.children.length + 1);

    function highlightHashtags(text) {
        return text.replace(/(#\w+)/g, '<span class="hashtag" style="color: #1d9bf0;">$1</span>');
    }

    const processedContent = highlightHashtags(content);

    const html = `
        <div id="${postId}" class="post">
            <div class="post-details">
                <div><img class="image-user" src="../../data/icons/n_icon.png" alt="n_icon"></div>
                <div id="mid">
                    <div class="names">
                        <strong>${newPost.username}</strong> <span id="username">${newPost.handle}</span> 
                        <span id="dot"></span> 
                        <span id="time">${newPost.time}</span>
                    </div>
                    <div class="content">
                        ${processedContent}
                    </div>
                </div>
                <div id="dots">...</div>
            </div>
            <div class="post-options">
                <div></div>
                <div class="icons">
                    <img class="image" src="../../data/icons/comment_icon.png" alt="comment_icon">
                    <img class="image" src="../../data/icons/repost_icon.png" alt="repost_icon">
                    <img class="image" src="../../data/icons/like_icon.png" alt="like_icon">
                    <img class="image" src="../../data/icons/analytics_icon.png" alt="analytics_icon">
                    <img class="image" src="../../data/icons/bookmark_dark_icon.png" alt="bookmark_dark_icon">
                </div>
                <div class="upload">
                    <img class="image" src="../../data/icons/upload_icon.png" alt="upload_icon">
                </div>
            </div>
        </div>
    `;
    const newDiv = document.createElement("div");
	newDiv.innerHTML = html;
	newDiv.classList.add("post-text");
    const firstPost = postsContainer.firstChild;
    postsContainer.insertBefore(newDiv, firstPost);
}

const tweetInput = document.querySelector('.post-input input');
const postButton = document.getElementById('postButton');

postButton.addEventListener('click', function() {
    if (!postButton.classList.contains('inactive')) {
        const tweetContent = tweetInput.value;

        createNewPost(tweetContent);

        tweetInput.value = '';

        postButton.classList.add('inactive');
        postButton.style.color = '';
        postButton.style.backgroundColor = '';
    }
});

tweetInput.addEventListener('input', function() {
    if (tweetInput.value.length > 0) {
        postButton.classList.remove('inactive');
        postButton.style.color = 'white';
        postButton.style.backgroundColor = '#4696e6';
    } else {
        postButton.classList.add('inactive');
        postButton.style.color = '';
        postButton.style.backgroundColor = '';
    }
});

const messagePageText = document.getElementById('messages');
const messagePageImage = document.getElementById('messageImage');
messagePageText.style.cursor = 'pointer';
messagePageImage.style.cursor = 'pointer';


messagePageText.addEventListener('click', () => {
    window.location.href = '/twitter2/public/chat/chat.html';
});

messagePageImage.addEventListener('click', () => {
    window.location.href = '/twitter2/public/chat/chat.html';
});

const fetchPosts = async (page = 1, pageSize = 5) => {
    try {
        const response = await fetch(`/api/posts?page=${page}&pageSize=${pageSize}`);
        if (response.ok) {
        const data = await response.json();
        return data;
        } else {
        throw new Error('Failed to fetch posts');
        }
    } catch (error) {
        console.error(error);
        throw error;
    }
};
  
const renderPosts = async () => {
try {
    const posts = await fetchPosts();
    const postsContainer = document.querySelector('.posts');
    posts.forEach(post => {
    const postElement = document.createElement('div');
    postElement.classList.add('post');
    postElement.id = post.id;
    postElement.innerHTML = `
        <div class="post-details">
        <div>
            <img class="image-user" src="../../data/icons/n_icon.png" alt="n_icon">
        </div>
        <div id="mid">
            <div class="names">
            <strong>${post.name}</strong> <span id="username">${post.username}</span> 
            <span id="dot"></span> 
            <span id="time">${post.time}</span>
            </div>
            <div class="content">
            ${post.content}
            </div>
        </div>
        <div id="dots">...</div>
        </div>
        <div class="post-options">
        <div></div>
        <div class="icons">
            <img class="image" src="../../data/icons/comment_icon.png" alt="comment_icon">
            <img class="image" src="../../data/icons/repost_icon.png" alt="repost_icon">
            <img class="image" src="../../data/icons/like_icon.png" alt="like_icon">
            <img class="image" src="../../data/icons/analytics_icon.png" alt="analytics_icon">
            <img class="image" src="../../data/icons/bookmark_dark_icon.png" alt="bookmark_dark_icon">
        </div>
        <div class="upload">
            <img class="image" src="../../data/icons/upload_icon.png" alt="upload_icon">
        </div>
        </div>
    `;
    postsContainer.appendChild(postElement);
    });
} catch (error) {
    console.error(error);
}
};

window.addEventListener('load', renderPosts);

window.addEventListener('scroll', () => {
if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
    renderPosts();
}
});