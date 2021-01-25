/**
 * @description Code final de la partie 7 de l'épisode 3.
 * 				Ce fichier permet d'ouvrir un serveur sur le port 8080 et d'envoyer les données reçues vers un deuxième serveur ouvert sur le port 8081.
 * 				Ces deux serveur permettent de faire communiquer l'application Android (partie déplacement du point) avec l'Arduino 
 * 					et l'application Android (partie de visualisation de la matrice).
 */

 var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 });
let output;

const server = require('net').createServer((socket) => {
	socket.on('data', (data) => {
		//socket.write(data.toString());
		if (output) {
			//console.log("Message envoyé Android : " + data.toString());
			output.write(data.toString() + '\n');
		}
		port.write(data.toString(), (err) => {
			if (err) console.log(err);
			else {
				console.log("Message reçu : " + data.toString());
			}
		});
	});
});

const serverMatrice = require('net').createServer((socketMatrice) => {
	output = socketMatrice;
	//On demande les coordonnées à l'Arduino
	port.write('c', (err) => {
		if (err) console.log(err);
		else {
			console.log("Demande coordonnée à l'Arduino");
		}
	});
	port.on('data', function (data) {
		console.log("Coordonnées LED : " + data.toString());
		output.write(data.toString());
		data = null;
	});
});

server.listen(8080, () => {
	console.log("Server opened on", server.address().port);
});

serverMatrice.listen(8081, () => {
	console.log("Server Matrice opened on", serverMatrice.address().port);

});