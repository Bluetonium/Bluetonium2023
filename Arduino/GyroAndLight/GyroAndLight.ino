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

void createRainbow(double ratio, int index) {

  int normalized = int(ratio * 256 * 6);

  //find the region for this position
  int region = normalized / 256;

  //find the distance to the start of the closest region
  int x = normalized % 256;

  uint8_t r = 0, g = 0, b = 0;
  switch (region)
  {
    case 0: r = 255; g = 0;   b = 0;   g += x; break;
    case 1: r = 255; g = 255; b = 0;   r -= x; break;
    case 2: r = 0;   g = 255; b = 0;   b += x; break;
    case 3: r = 0;   g = 255; b = 255; g -= x; break;
    case 4: r = 0;   g = 0;   b = 255; r += x; break;
    case 5: r = 255; g = 0;   b = 255; b -= x; break;
  }
  leds[index] = CRGB(r, g, b);
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
      case 'w'://this is jsut for fun, if it causes it to malfunction in anyway just remove it
        double range = 101.0;
        int counter = 0;
        for (int i = 0; i < NUM_LEDS; i++) {
          createRainbow(i / range, counter);
          counter++;
        }//im mostly sure that the range and counter variables will be deallocated automatically but who knows
        FastLED.show();
        break;
    }
  }
}
