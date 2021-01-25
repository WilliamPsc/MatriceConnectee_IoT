/**
 * @description Code pour la partie 4 de l'épisode 3
 * 				Il permet de recevoir des données sur le serveur créé puis de transmettre cette valeur à l'Arduino
 */

var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 }); // Création du port série sur le port /dev/ttyAMA0 avec un bitrate de 115200
var server = require('net').createServer((socket) => { // Création serveur
	socket.on('data', (data) => { // si on reçoit une data
		port.write(data.toString(), (err) => { // on écrit la data sur le port série
			if (err) {
				console.log(err);
			}
			else {
				console.log('message ecrit'); // on envoi le message dans la console
				console.log(data.toString());
			}
		});
	});
});

server.listen(8080, () => { // on lance le serveur sur le port 8080 et on écoute sur ce port
	console.log('Opened server on ', server.address().port);
});