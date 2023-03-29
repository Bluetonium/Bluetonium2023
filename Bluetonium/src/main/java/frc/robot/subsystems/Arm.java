package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Arm extends SubsystemBase {

  public CANSparkMax arm = null;
  public CANSparkMax feed = null;

  public int firstHueValue = 0;

  public Arm() {
    arm = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
    feed = new CANSparkMax(Constants.FEED_MOTOR, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
  }

  public void armSpeed(double speed) {
    arm.set(speed);
  }

  public void feedSpeed(double speed) {
    feed.set(speed);
  }

  public void Color(char color) {
    Short r;
    short g;
    short b;
    switch (color) {
      case 'y':
        r = 255;
        g = 255;
        b = 0;
        break;
      case 'p':
        r = 160;
        g = 32;
        b = 240;
        break;
      case 'n':
        r = 0;
        g = 0;
        b = 0;
        break;
      case 's':
        r = 0;
        g = 255;
        b = 0;
        break;
      default:
        r = 0;
        g = 0;
        b = 0;
        break;
    }
    double currentR = RobotContainer.m_ledBuffer.getLED(0).red;
    double currentG = RobotContainer.m_ledBuffer.getLED(0).green;
    double currentB = RobotContainer.m_ledBuffer.getLED(0).blue;

    if (currentR == r && currentG == g && currentB == b) {
      return;
    } else {
      System.out.printf("R %f G %f B %f\n", currentR, currentG, currentB);
    }
    for (int i = 0; i < Constants.NUMBER_OF_LEDS; i++) {
      RobotContainer.m_ledBuffer.setRGB(i, r, g, b);
    }
    RobotContainer.m_led.setData(RobotContainer.m_ledBuffer);
  }

  // they call me Mr.Comedy
  public void rainbow() {
    for (var i = 0; i < RobotContainer.m_ledBuffer.getLength(); i++) {
      final var hue = (firstHueValue + (i * 180 / RobotContainer.m_ledBuffer.getLength())) % 180;
      RobotContainer.m_ledBuffer.setHSV(i, hue, 255, 128);
    }

    firstHueValue += 3;
    firstHueValue %= 180;

    RobotContainer.m_led.setData(RobotContainer.m_ledBuffer);
  }
}
