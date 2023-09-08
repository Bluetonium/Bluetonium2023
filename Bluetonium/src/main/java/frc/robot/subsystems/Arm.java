package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.ArmConstants;
import frc.robot.utils.Constants.LedConstants;
import frc.robot.RobotContainer;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.utils.Constants.ledColors;

public class Arm extends SubsystemBase {

  public CANSparkMax mainArm = null;
  public CANSparkMax feed = null;
  public CANSparkMax miniArm = null;
  public CANSparkMax miniFeed = null;
  public DigitalInput stopSwitch = null;

  public double mainArmZeroOffset;
  public double miniArmZeroOffset;

  public DutyCycleEncoder miniArmPosition;
  public DutyCycleEncoder mainArmPostion;

  public SparkMaxPIDController mainArmPID;
  public SparkMaxPIDController miniArmPID;

  public int firstHueValue = 0;

  private SlewRateLimiter mainArmFilter;
  private SlewRateLimiter miniArmFilter;

  private ledColors currentColor = ledColors.OFF;

  private boolean softLimitsEnabled = true;

  public Arm() {
    mainArm = new CANSparkMax(ArmConstants.ARM_MOTOR, MotorType.kBrushless);
    feed = new CANSparkMax(ArmConstants.FEED_MOTOR, MotorType.kBrushless);
    miniArm = new CANSparkMax(ArmConstants.MINI_ARM_MOTOR, MotorType.kBrushless);
    miniFeed = new CANSparkMax(ArmConstants.MINI_FEED_MOTOR, MotorType.kBrushless);
    stopSwitch = new DigitalInput(ArmConstants.STOP_SWITCH);

    miniArm.setInverted(false);
    miniFeed.setInverted(false);

    miniArmPosition = new DutyCycleEncoder(ArmConstants.MINI_ARM_ENCODER_PORT);
    mainArmPostion = new DutyCycleEncoder(ArmConstants.MAIN_ARM_ENCODER_PORT);
    mainArmFilter = new SlewRateLimiter(0.3d);
    miniArmFilter = new SlewRateLimiter(0.3d);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Main Arm Position",getMainArmPos());
    SmartDashboard.putNumber("Mini Arm Position",getMiniArmPos());
    SmartDashboard.putNumber("Arm Speed",mainArm.getEncoder().getVelocity());
    SmartDashboard.putNumber("Mini Arm Speed",miniArm.getEncoder().getVelocity());
    SmartDashboard.putNumber("Arm Feed",feed.getEncoder().getVelocity());
    SmartDashboard.putNumber("Mini Arm Feed",miniFeed.getEncoder().getVelocity());
    SmartDashboard.putString("Color",currentColor.name());
    SmartDashboard.putBoolean("Soft Limits Enabled",softLimitsEnabled);
    SmartDashboard.updateValues();
  }

  public void mainArmSpeed(double speed) {
    SmartDashboard.putNumber("MainArmInput", speed);
    SmartDashboard.updateValues();
    if (!CheckMiniArmOut() || !softLimitsEnabled) {
      if (Math.abs(speed) < ArmConstants.ARM_SPEED_DEADZONE) {
        mainArmFilter.reset(0);
        mainArm.set(0);
        return;
      }
      mainArm.set(mainArmFilter.calculate(speed));
    } else {
      RobotContainer.driverController2.setRumble(RumbleType.kBothRumble, 1);
      mainArm.set(0);
    }
    
  }

  public void miniArmSpeed(double speed) {
    if (!CheckMainArmOut() || !softLimitsEnabled) {
      if (Math.abs(speed) < ArmConstants.ARM_SPEED_DEADZONE) {
        miniArmFilter.reset(0);
        miniArm.set(0);
        return;
      }
       miniArm.set(miniArmFilter.calculate(speed));
    } else {
      RobotContainer.driverController2.setRumble(RumbleType.kBothRumble, 1);
      miniArm.set(0);
    }
  }

  public void miniFeedSpeed(double speed) {
    miniFeed.set(speed);
  }

  public void feedSpeed(double speed) {
    feed.set(speed);
  }

  public double getMiniArmPos() {
    return miniArmPosition.getAbsolutePosition();

  }

  public double getMainArmPos() {
    return mainArmPostion.getAbsolutePosition();
  }

  public void Color(ledColors newColor) {// switch this to using a enum and an array for rgb
    if (currentColor == newColor)
      return;
    currentColor = newColor;
    Short r;
    short g;
    short b;
    switch (newColor) {
      case YELLOW:
        r = 255;
        g = 255;
        b = 0;
        break;
      case PURPLE:
        r = 160;
        g = 32;
        b = 240;
        break;
      default:
        r = 0;
        g = 0;
        b = 0;
        break;
    }
    for (int i = 0; i < LedConstants.NUMBER_OF_LEDS; i++) {
      RobotContainer.m_ledBuffer.setRGB(i, r, g, b);
    }
    RobotContainer.m_led.setData(RobotContainer.m_ledBuffer);
  }

  private boolean CheckMainArmOut() {
    return getMainArmPos() < ArmConstants.MAIN_ARM_OUT_THRESHOLD;// janky
  }

  private boolean CheckMiniArmOut() {
    return getMiniArmPos() > ArmConstants.MINI_ARM_OUT_THRESHOLD;
  }
}
