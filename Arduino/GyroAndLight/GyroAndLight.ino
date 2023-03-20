#include "Wire.h"
#include <MPU6050_light.h>
#include <FastLED.h>

#define LED_PIN 6
#define NUM_LEDS 100

MPU6050 mpu(Wire);
char input = 'a';
double ang = 0.0;
long timer = 0;
bool startedSuccesfully = false;
long lastCheckedTime = 0;

CRGB leds[NUM_LEDS];

void setLeds(int r, int g, int b)
{
  for (int i = 0; i < NUM_LEDS; i++)
  {
    leds[i] = CRGB(r, g, b);
  }
  FastLED.show();
}
void setup()
{
  FastLED.addLeds<WS2812, LED_PIN, GRB>(leds, NUM_LEDS);
  Serial.begin(115200);
  Wire.begin();
  byte status = mpu.begin();
  if (status != 0)
  {
    setLeds(255, 0, 0);
    while (true) {
      setLeds(255, 0, 0);
      delay(500);
      setLeds(0, 0, 0);
      delay(500);
    }
  }
}

void loop()
{
  mpu.update();
  if (millis() - timer > 100) {
    ang = mpu.getAngleY();
    timer = millis();
  }
  if (!startedSuccesfully && millis() - lastCheckedTime > 5000) {
    setLeds(255, 0, 0);
    delay(100);
    setLeds(0, 0, 0);
    lastCheckedTime = millis();
  }
  if (Serial.available() > 0) {//check that there is atleast 1 bye of data to read
    input = Serial.read();
    switch (input) {
      case 'y':
        setLeds(255, 255, 0);
        break;
      case 'p':
        setLeds(148, 0, 211);
        break;
      case 'n':
        setLeds(0, 0, 0);
        break;
      case 'g':
        Serial.println(ang);
        break;
      case 's':
        setLeds(0, 255, 0);
        startedSuccesfully = true;
        Serial.println("connected!");
        break;
    }
  }
}
