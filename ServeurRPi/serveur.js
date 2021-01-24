var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 });


// On crée un serveur sur cette machine (raspberry pi 3)
var server = require('net').createServer((socket) => {

  // On crée un socket.
  socket.on('data', (data) => {
    // Quand il reçoit une donnée, le contenu de ce bloc sera executé
    console.log("Socket received : " + data.toString());
    port.write(data.toString(), (err) => {
      if (err) console.log(err);
      else {
        if (data.toString() == "1" || data.toString() == "0") {
          console.log("Message reçu : " + data.toString());
        }
      }
    });
  });
});

console.log("Script lance");

// Le serveur écoutera le port 8080 et executera le block débutant à la ligne 14 si il reçoit une donnée
server.listen(8080, () => {
  console.log('opened server on', server.address().port);
});
