var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0', {baudRate: 115200});
let output;

var server = require('net').createServer((socket) => {
	socket.on('data', (data) => {
		socket.write(data.toString());
		port.write(data.toString(), (err) => {
			if(err) console.log(err);
			else{
				console.log("Message reçu : " + data.toString());
			}
		});
	});
	if (output) {
		output.write(data.toString());
	}
});

console.log("Script lancé!");

server.listen(8080, () => {
	console.log("Server opened on", server.address().port);
});

var serverMatrice = require('net').createServer((socketMatrice) => {
	/*
	socketMatrice.on('data', (data) => {
		socketMatrice.write(data.toString());
		console.log("Message envoyé à la matrice : " + data.toString());
	});
	*/
	output = matriceSocket;
});

serverMatrice.listen(8081, () => {
	console.log("Server Matrice opened on", serverMatrice.address().port);
	
})