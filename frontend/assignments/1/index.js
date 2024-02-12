const newPost = {
    username: 'Nitesh Gupta',
    handle: '@nit_hck',
    time: 'Just now',
    tags: ['Trending', 'Excited']
};

function createNewPost(content) {
    const html = `
        <div class="post">
            <div class="post-details">
                <div><img class="image-user" src="icons/n_icon.png" alt="n_icon"></div>
                <div id="mid">
                    <div class="names">
                        <strong>${newPost.username}</strong> <span id="username">${newPost.handle}</span> 
                        <span id="dot"></span> 
                        <span id="time">${newPost.time}</span>
                    </div>
                    <div class="content">
                        ${content}
                        <span>${newPost.tags.map(tag => `&num;${tag}`).join(' ')}</span>
                    </div>
                </div>
                <div id="dots">...</div>
            </div>
            <div class="post-options">
                <div></div>
                <div class="icons">
                    <img class="image" src="icons/comment_icon.png" alt="comment_icon">
                    <img class="image" src="icons/repost_icon.png" alt="repost_icon">
                    <img class="image" src="icons/like_icon.png" alt="like_icon">
                    <img class="image" src="icons/analytics_icon.png" alt="analytics_icon">
                    <img class="image" src="icons/bookmark_dark_icon.png" alt="bookmark_dark_icon">
                </div>
                <div class="upload">
                    <img class="image" src="icons/upload_icon.png" alt="upload_icon">
                </div>
            </div>
        </div>
    `;
    const newDiv = document.createElement("div");
	newDiv.innerHTML = html;
	newDiv.classList.add("post-text");
	const postsContainer = document.querySelector(".posts");
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
    }
});

// Event listener for input in the tweet input field
tweetInput.addEventListener('input', function() {
    if (tweetInput.value.length > 0) {
        postButton.classList.remove('inactive');
    } else {
        postButton.classList.add('inactive');
    }
});
