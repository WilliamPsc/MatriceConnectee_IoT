/**
 * @description On se connecte au serveur en paramètre sur le port 8080
 *              Ensuite, le programme attend une input clavier afin de l'envoyer au serveur une fois que l'utilisateur a appuyé sur ENTREE
 */

// Connexion au serveur
var client = require('net').connect(8080, '192.168.43.105', function () {
    console.log("Connected");
});


// Création du module d'attente d'une saisie clavier
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin, // input standard : clavier
    output: process.stdout, // sortie standard
});

rl.prompt(); // affiche ">"

rl.on('line', (line) => {
    switch (line.trim()) {
        case 'h':
            client.write("h");
            break;
        case 'b':
            client.write("b");
            break;
        case 'g':
            client.write("g");
            break;
        case 'd':
            client.write("d");
            break;
        case 'a':
            client.write("a");
            break;
        case 'exit':
            client.write("exit");
            client.destroy(); // Permet de se déconnecter du serveur proprement
            process.exit(0);
            break;
        default:
            break;
    }
    rl.prompt();
}).on('close', () => {
    process.exit(0);
});
