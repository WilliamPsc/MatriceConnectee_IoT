let message;
let output;

const commandeServeur = require('net').createServer((commandeSocket) => {
  commandeSocket.on('data', (data) => {
    message = data.toString();
    console.log("Serveur commande : " + message);

    if (output) {
      output.write(message + '\n');
    }
  });
});

const matriceServeur = require('net').createServer((matriceSocket) => {
  output = matriceSocket;
});

commandeServeur.listen(3000, () => {
    console.log('commandeServeur:', commandeServeur.address().port);
});

matriceServeur.listen(3001, () => {
  console.log('matriceServeur:', matriceServeur.address().port);
});

console.log("Script lancé !");