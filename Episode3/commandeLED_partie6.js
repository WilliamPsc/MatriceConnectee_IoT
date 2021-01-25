/**
 * @description Code pour la partie 5 de l'épisode 3
 * 				Il permet de recevoir des données sur le serveur créé puis de transmettre cette valeur à l'Arduino 
 * 					ainsi que renvoyer la donnée sur la socket donc à toutes les personnes connectées
 */

var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 });

var server = require('net').createServer((socket) => {
	socket.on('data', (data) => {
		socket.write(data.toString());
		port.write(data.toString(), (err) => {
			if (err) console.log(err);
			else {
				console.log("Message reçu : " + data.toString());
			}
		});
	});
});

server.listen(8080, () => {
	console.log("Server opened on", server.address().port);
});
