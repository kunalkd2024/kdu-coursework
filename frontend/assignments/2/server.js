const express = require("express");
const http = require("http");
const path = require("path");
const userLoginRouter = require("./routes/login");
const mainPageRouter = require("./routes/main-page");

const cors = require("cors");
const app = express();
const server = http.createServer(app);
const socket = require("socket.io");

app.use(express.json());
app.use(cors());

app.use("/api", userLoginRouter);
app.use("/api", mainPageRouter);

const io = new socket.Server(server, {
  cors: {
    origin: "http://127.0.0.1:5500",
    methods: ["GET", "POST"],
  },
});

app.use(express.static(path.join(__dirname, "public")));

const activeUsers = {};

io.on("connection", (socket) => {
  console.log("A user connected");
  // console.log(socket);
  socket.on("message", (data) => {
    console.log(data.message);
  });

  socket.on("refertoMainpage", async (data) => {
    console.log(data.message);

    try {
      const redirectResponse = await fetch("http://localhost:3000/api/posts");
      const redirectData = await redirectResponse.json();
      if (redirectData.redirectUrl) {
        socket.emit("redirect", { redirectUrl: redirectData.redirectUrl });
      } else {
        console.error("Redirect URL not found");
      }
    } catch (error) {
      console.error("Error fetching redirect URL:", error);
    }
  });

  socket.on("login", (user) => {
    console.log(`User ${user} logged in`);
    activeUsers[user] = socket.id;
    console.log(activeUsers);
  });

  socket.on("chatMessage", (payload) => {
    const { sender, receiver, message } = payload;
    io.except(socket.id).emit("new-message", { receiver, message });
  });

  socket.on("disconnect", () => {
    console.log("Disconnecting...");
    const disconnectedUser = Object.keys(activeUsers).find(
      (key) => activeUsers[key] === socket.id
    );
    delete activeUsers[disconnectedUser];
  });
});

app.get("/users", (req, res) => {
  //console.log(activeUsers);
  res.send(activeUsers);
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
