package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Drivetrain extends SubsystemBase {
  public CANSparkMax frontLeftSpark = null;
  public CANSparkMax backLeftSpark = null;

  public CANSparkMax frontRightSpark = null;
  public CANSparkMax backRightSpark = null;

  public MotorControllerGroup leftMotors = null;
  public MotorControllerGroup rightMotors = null;

  public DifferentialDrive dDrive = null;

  public Drivetrain() {

    frontLeftSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT, MotorType.kBrushed);
    backLeftSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK, MotorType.kBrushed);

    backLeftSpark.follow(frontLeftSpark);

    frontRightSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT, MotorType.kBrushed);
    backRightSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK, MotorType.kBrushed);

    backRightSpark.follow(frontRightSpark);

    leftMotors = new MotorControllerGroup(frontLeftSpark, backLeftSpark);
    rightMotors = new MotorControllerGroup(frontRightSpark, backRightSpark);

    // leftMotors.setInverted(true);//make it so forward is forward
    rightMotors.setInverted(true);

    dDrive = new DifferentialDrive(leftMotors, rightMotors);

  }

  public void rumble(double value) {
    RobotContainer.driverController1.setRumble(RumbleType.kBothRumble, value);
  }

  public void arDrive(double speed, double turnSpeed) {
    dDrive.arcadeDrive(speed, turnSpeed);
  }

  @Override
  public void periodic() {

  }
}
