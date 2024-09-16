const messageInput = document.getElementById("msgInput");
const sendButton = document.getElementById("sendMessage");
const messageOutput = document.getElementById("chat");

function addMessage(from, message) {
  const element = document.createElement("div");

  const messageContent = document.createElement("span");
  messageContent.textContent = message;
  element.appendChild(messageContent);
  const time = document.createElement("span");

  if (from === "You") {
    element.classList.add("you");
    messageContent.classList.add("you-message");
  } else {
    element.classList.add("user");
    messageContent.classList.add("user-message");
  }
  time.classList.add("time");

  const currentTime = new Date();
  const hours = currentTime.getHours() % 12 || 12;
  const minutes = String(currentTime.getMinutes()).padStart(2, "0");
  const ampm = currentTime.getHours() < 12 ? "AM" : "PM";
  time.textContent = `${hours}:${minutes} ${ampm}`;
  element.appendChild(time);

  messageOutput.appendChild(element);
}

sendButton.addEventListener("click", () => {
  const message = messageInput.value;
  socket.emit("chatMessage", { sender: "You", receiver: "User", message });
  addMessage("You", message);
  messageInput.value = "";
});

socket.on("new-message", (data) => {
  const { receiver, message } = data;
  addMessage(receiver, message);
});

const homePageText = document.getElementById("home");
const homePageImage = document.getElementById("homeImage");
homePageText.style.cursor = "pointer";
homePageImage.style.cursor = "pointer";

homePageText.addEventListener("click", () => {
  window.location.href = "/twitter2/public/main-page/main.html";
});

homePageImage.addEventListener("click", () => {
  window.location.href = "/twitter2/public/main-page/main.html";
});

var responseData = {};
fetch("http://localhost:3000/users")
  .then((response) => response.json())
  .then((data) => {
    console.log(data);
    responseData = data;
    console.log(responseData);
    displayUserList(responseData);
  })
  .catch((error) => {
    console.error("Error fetching data:", error);
  });

function displayUserList(users) {
  var userListDiv = document.getElementById("users");
  var userListHTML = "<ul>";
  // if (users.size() == 1) {
  //   userListHTML +=
  //     '<img src="../../data/icons/user2.png" alt="user2" /> <span><strong>Sagun Sangwan</strong></span><span id="username">@ShagunSangwan15</span>';
  // }
  for (var key in users) {
    if (users.hasOwnProperty(key)) {
      if (key !== "null" || key !== "undefined")
        userListHTML +=
          "<div class = 'activeUsers'>" +
          '<img src="../../data/icons/user2.png" alt="user2" />' +
          key +
          "<br>" +
          "</div>";
    }
  }
  userListHTML +=
    '<div class = "activeUsers"><img src="../../data/icons/user2.png" alt="user2" /> <span><strong>Nitesh Gupta</strong></span></div>';
  userListHTML += "</ul>";
  userListDiv.innerHTML = userListHTML;
}
