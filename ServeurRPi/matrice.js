var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', { baudRate: 115200 });
let output;

const server = require('net').createServer((socket) => {
	socket.on('data', (data) => {
		//socket.write(data.toString());
		if (output) {
			console.log("Message envoyé Android : " + data.toString());
			output.write(data.toString() + '\n');

			port.write(data.toString(), (err) => {
				if (err) console.log(err);
				else {
					console.log("Message reçu : " + data.toString());
				}
			});
		}
	});
});

const serverMatrice = require('net').createServer((socketMatrice) => {
	output = socketMatrice;
	//On demande coordonnée à l'Arduino
	port.write('c', (err) => {
		if (err) console.log(err);
		else {
			console.log("Demande coordonnée à l'Arduino");
		}
	});
	port.on('data', function (data) {
		console.log("Coordonnées LED : " + data.toString());
		output.write(data.toString());
	});
});

console.log("Script lancé!");

server.listen(8080, () => {
	console.log("Server opened on", server.address().port);
});

serverMatrice.listen(8081, () => {
	console.log("Server Matrice opened on", serverMatrice.address().port);

});