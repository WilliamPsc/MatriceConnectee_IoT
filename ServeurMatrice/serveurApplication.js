var SerialPort = require('serialport');

// On crée un serveur sur cette machine
var server = require('net').createServer((socket) => {

// On crée un socket.
  socket.on('data', (data) => {
    // Quand il reçoit une donnée, le contenu de ce bloc sera executé
    console.log("3000 Socket received : " + data.toString());
    socket.write(data.toString());

  });
});

console.log("Script lance");

// Le serveur écoutera le port 3000 et executera le block si il reçoit une donnée
server.listen(3000, ()=>{
    console.log('3000 opened server on', server.address().portx);
});