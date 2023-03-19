/* Get tilt angles on X and Y, and rotation angle on Z
   Angles are given in degrees

   License: MIT
*/

#include "Wire.h"
#include <MPU6050_light.h>
#include <FastLED.h>

#define LED_PIN 6
#define NUM_LEDS 144

MPU6050 mpu(Wire);
String serialData;
char input = 'a';
double ang = 0.0;
long timer = 0;

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
  Serial.println("starting");
  Wire.begin();
  byte status = mpu.begin();
  if (status != 0)
  {
    setLeds(255, 0, 0);
    while (true)
    {
    };
  }
  else
  {
    setLeds(0, 255, 0);
  }
}

void loop()
{

  if (millis() - timer > 100) {
    ang = mpu.getAngleY();
    timer = millis();
  }
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
  }
  mpu.update();

}
