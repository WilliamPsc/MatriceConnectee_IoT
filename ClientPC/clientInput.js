// On crée un serveur sur cette machine (raspberry pi 3)
var client = require('net').connect(8080, '192.168.43.105', function () {
    console.log("Connected");
});

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.prompt();

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
            client.destroy();
            process.exit(0);
            break;
        default:
            break;
    }
    rl.prompt();
}).on('close', () => {
    process.exit(0);
});

client.on('data', (message) => {
    console.log(message.toString());
});
