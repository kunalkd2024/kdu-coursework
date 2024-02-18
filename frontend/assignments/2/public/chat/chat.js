const socket = io("http://localhost:3000");

const messageInput = document.getElementById("msgInput");
const sendButton = document.getElementById("sendMessage");
const messageOutput = document.getElementById("chat");

function addMessage(from, message) {
    const element = document.createElement('div');

    const messageContent = document.createElement('span');
    messageContent.textContent = message;
    element.appendChild(messageContent);
    const time = document.createElement('span');
    
    if(from === "You") {
        element.classList.add('you');
        messageContent.classList.add('you-message');
    }
    else{
        element.classList.add('user');
        messageContent.classList.add('user-message');
    }
    time.classList.add('time');

    const currentTime = new Date();
    const hours = currentTime.getHours() % 12 || 12;
    const minutes = String(currentTime.getMinutes()).padStart(2, '0');
    const ampm = currentTime.getHours() < 12 ? 'AM' : 'PM';
    time.textContent = `${hours}:${minutes} ${ampm}`;
    element.appendChild(time);

    messageOutput.appendChild(element);
}

sendButton.addEventListener("click", () => {
    const message = messageInput.value;
    socket.emit('chatMessage', { sender: 'You', receiver: 'User', message });
    addMessage("You", message);
    messageInput.value = '';
});

socket.on('new-message', (data) => {
    const { receiver, message } = data;
    addMessage(receiver, message);
});

socket.emit('login', 'User');

const homePageText = document.getElementById('home');
const homePageImage = document.getElementById('homeImage');
homePageText.style.cursor = 'pointer';
homePageImage.style.cursor = 'pointer';

homePageText.addEventListener('click', () => {
    window.location.href = '/twitter2/public/main-page/main.html';
});

homePageImage.addEventListener('click', () => {
    window.location.href = '/twitter2/public/main-page/main.html';
});