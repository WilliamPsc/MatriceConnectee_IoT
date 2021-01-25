/**
 * @description Code pour la partie 3 de l'épisode 3
 * 				Il permet d'envoyer la valeur 0 ou 1 sur le port série grâce à une fonction active toute les secondes
 */

var SerialPort = require('serialport');
var port = new SerialPort('/dev/ttyAMA0',{baudRate: 115200});
var c='1';
port.on("open",()=>{
	console.log('serial port open');
	setInterval(mafonction,1000)
});

function mafonction(){
	port.write(c,(err) =>  {
		if(err) {console.log(err);}
		else{
			if(c=='1') c='0';
			else c='1';
		}
	});
}
