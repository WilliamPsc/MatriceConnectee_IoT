var SerialPort = require('serialport');

// On crée un serveur sur cette machine
var serverMatrice = require('net').createServer((socketMatrice) => {

    // On crée un socket.
    socketMatrice.on('data', (data) => {
      // Quand il reçoit une donnée, le contenu de ce bloc sera executé
        console.log("3001 Socket received : " + data.toString());
        socketMatrice.write("3000 : " + data.toString() + "\n");
    
    });
});

console.log("Script lance");

// Le serveur écoutera le port 3001 et executera le block si il reçoit une donnée
serverMatrice.listen(3001, ()=>{
  console.log('3001 opened server on', serverMatrice.address().portx);
});