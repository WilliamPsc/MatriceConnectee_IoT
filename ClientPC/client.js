// On crée un serveur sur cette machine (raspberry pi 3)
var client = require('net').connect(8080, '192.168.43.105', function() {
	console.log("Connected");
});

client.on('data', (message) => {
	console.log(message.toString());
});

setInterval(sendData, 1500);
var c = '1'; // u = 1 / v = 0

function sendData(){
	client.write(c);
	if(c == '1') c = '0';
	else c = '1';
}
