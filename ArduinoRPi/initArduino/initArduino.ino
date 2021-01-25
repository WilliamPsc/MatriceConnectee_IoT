/**
 * @description Ce fichier Arduino permet d'allumer ou éteindre une LED selon la donnée reçue sur le port série. 
 *              La LED allumée sera la LED 13 (LED_BUILTIN) et sera allumée si l'arduino reçoit 1 sur le port série.
 */


void setup() {
  // Initialize serial communication at 115200 bits per second:
  Serial.begin(115200);
  // PIN 13 en output
  pinMode(LED_BUILTIN, OUTPUT);
}

// loop
void loop() {
  // Lecture et attente d'une donnée sur le port série
  while (Serial.available()) // Vérifie si au moins une donnée est disponible
  {
    char etat = Serial.read();

    if (etat == '1') {
      digitalWrite(LED_BUILTIN, HIGH); // On allume la LED
    } else {
      if(etat == '0') digitalWrite(LED_BUILTIN, LOW); // On éteint la LED
    }
    delay(1000); // délai de 1s
  }
}
