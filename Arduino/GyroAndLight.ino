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
  Serial.begin(9600);
  Serial.println("Starting");
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
    delay(500);
    setLeds(0, 0, 0);
  }
}

void serialEvent()
{
  serialData = Serial.readString();
  if (serialData == "CONE\n")
  {
    setLeds(255, 255, 0);
  }
  else if (serialData == "CUBE\n")
  {
    setLeds(148, 0, 211);
  }
  else if (serialData == "NONE\n")
  {
    setLeds(0, 0, 0);
  }
  else if (serialData == "GETPITCH\n")
  {
    Serial.println(mpu.getAngleY());
  }
  else
  {
    Serial.println("Unknown : " + serialData);
  }
}

void loop()
{
  mpu.update();
}
