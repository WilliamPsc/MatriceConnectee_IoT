// On crée un serveur sur cette machine (raspberry pi 3)
var client = require('net').connect(8080, '192.168.43.105', function() {
	console.log("Connected");
});

client.on('data', (message) => {
	console.log(message.toString());
});

setInterval(sendData, 1500);

function sendData(){
	var val = Math.floor(Math.random() * (4 - 0) + 0);
	//var val = 2;

	if(val == 0){
		client.write("h");
		console.log("Envoi : h");
	} else if(val == 1){
		client.write("b");
		console.log("Envoi : b");
	} else if(val == 2){
		client.write("d");
		console.log("Envoi : d");
	} else if(val == 3){
		client.write("g");
		console.log("Envoi : g");
	}
	
}
