void setup() {
  // initialize serial communication at 115200 bits per second:
  Serial.begin(115200);
  // make the pushbutton's pin an input:
  pinMode(LED_BUILTIN, OUTPUT);
}

// the loop routine runs over and over again forever:
void loop() {
  // read the input pin:
  while ( Serial.available()) // Check to see if at least one character is available
  {
    char etat = Serial.read();
    // print out the state of the button:
    if (etat == '1') {
      digitalWrite(LED_BUILTIN, HIGH);
    } else {
      if(etat == '0') digitalWrite(LED_BUILTIN, LOW);
    }
    delay(1000);        // delay in between reads for stability
  }
}
