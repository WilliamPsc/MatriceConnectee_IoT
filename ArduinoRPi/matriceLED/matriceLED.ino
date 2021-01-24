#include "LedControl.h"

#define DIN 12
#define CLK 11
#define CS 10

LedControl lc = LedControl(DIN, CLK, CS, 1);

int pos[2] = {0,0};
bool masquer = true;

void setup() {
  // initialize serial communication at 115200 bits per second:
  Serial.begin(115200);

  lc.shutdown(0, false);
  lc.setIntensity(0,8);
  lc.clearDisplay(0);
  lc.setLed(0, pos[0], pos[1], true);
}

// the loop routine runs over and over again forever:
void loop() {
  // read the input pin:
  
  while ( Serial.available()){
    
    char etat = Serial.read();
    lc.setLed(0, pos[0], pos[1], false);
    // print out the state of the button:
    if (etat == 'd') {
      pos[0] = (pos[0] + 1) % 8;
    } else if(etat == 'g'){
      pos[0] = (pos[0] - 1);
      if(pos[0] < 0) pos[0] = 7;
    } else if(etat == 'b'){
      pos[1] = (pos[1] - 1);
      if(pos[1] < 0) pos[1] = 7;
    } else if(etat == 'h'){
      pos[1] = (pos[1] + 1) % 8;
    } else if(etat == 'a'){
      masquer = !masquer;
    } else if (etat == 'c'){ 
      Serial.println(pos[0]); delay(50);
      Serial.println(pos[1]);
    }

    
    
    lc.setLed(0, pos[0], pos[1], masquer);
    delay(100);        // delay in between reads for stability
  }
}
