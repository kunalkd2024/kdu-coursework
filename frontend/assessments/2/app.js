const express = require('express')
const cors = require('cors');
const http = require('http')
const app = express();

app.use(cors())
app.use(express.json())

const PORT = process.env.PORT || 3000;
const server = http.createServer(app);
const socket = require('socket.io');
const io = new socket.Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500"
    }
});

app.get('/', (req, res) => {
    res.json({
        "msg" : "Hello World!"
    })
    console.log("Running after response")
})

io.on('connection', (socket) => {
    console.log('Connection established')

    socket.on('message', (payload) => {
        io.except(socket.id).emit('new-message', payload)
    })

    socket.on('disconnect', () => {
        console.log("Disconnecting...")
    })
})

server.listen(PORT, () => {
    console.log(`App is started on ${PORT}`)
})