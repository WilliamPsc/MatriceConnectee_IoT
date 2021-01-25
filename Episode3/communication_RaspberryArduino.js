/**
 * @description Code pour la partie 3 de l'épisode 3
 * 				Il permet d'envoyer la valeur 0 sur le port série
 */

var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 });
port.on("open", () => {
	console.log('serial port open');
	port.write('0', (err) => {
		if (err) {
			console.log(err);
		}
		else {
			console.log('message ecrit');
		}
	});
});
