package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.ArmConstants;
import frc.robot.utils.Constants.MiscConstants;
import frc.robot.RobotContainer;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;

public class Arm extends SubsystemBase {

  public CANSparkMax arm = null;
  public CANSparkMax feed = null;
  public CANSparkMax miniArm = null;
  public CANSparkMax miniFeed = null;
  public DigitalInput stopSwitch = null;

  public double mainArmZeroOffset;
  public double miniArmZeroOffset;

  public RelativeEncoder miniArmPosition;
  public RelativeEncoder mainArmPostion;

  public SparkMaxPIDController mainArmPID;
  public SparkMaxPIDController miniArmPID;

  public int firstHueValue = 0;

  private SlewRateLimiter mainArmFilter;
  private SlewRateLimiter miniArmFilter;

  public Arm() {
    arm = new CANSparkMax(ArmConstants.ARM_MOTOR, MotorType.kBrushless);
    feed = new CANSparkMax(ArmConstants.FEED_MOTOR, MotorType.kBrushless);
    miniArm = new CANSparkMax(ArmConstants.MINI_ARM_MOTOR, MotorType.kBrushless);
    miniFeed = new CANSparkMax(ArmConstants.MINI_FEED_MOTOR,
        MotorType.kBrushless);
    stopSwitch = new DigitalInput(ArmConstants.STOP_SWITCH); // i dont fucking
    // know lol

    miniArm.setInverted(false);
    miniFeed.setInverted(false);

    miniArmPosition = miniArm.getEncoder();
    mainArmPostion = arm.getEncoder();
    mainArmFilter = new SlewRateLimiter(0.1d);
    miniArmFilter = new SlewRateLimiter(0.2);
  }

  @Override
  public void periodic() {
  }

  public void mainArmSpeed(double speed) {
    double calculatedValue = mainArmFilter.calculate(speed);
    arm.set(Math.min(speed, calculatedValue));
  }

  public void miniArmSpeed(double speed) {
    double calculatedValue = miniArmFilter.calculate(speed);
    miniArm.set(Math.min(speed, calculatedValue));
  }

  public void miniFeedSpeed(double speed) {
    miniFeed.set(speed);
  }

  public void feedSpeed(double speed) {
    feed.set(speed);
  }

  public double getMiniArmPos() {
    return miniArmPosition.getPosition() - miniArmZeroOffset;

  }

  public double getMainArmPos() {
    return mainArmPostion.getPosition() - mainArmZeroOffset;

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
        g = 125;
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
    }
    for (int i = 0; i < MiscConstants.NUMBER_OF_LEDS; i++) {
      RobotContainer.m_ledBuffer.setRGB(i, r, g, b);
    }
    RobotContainer.m_led.setData(RobotContainer.m_ledBuffer);
  }
}
