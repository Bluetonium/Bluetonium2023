package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.utils.Constants.DriveTrainConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends SubsystemBase {
  public CANSparkMax frontLeftSpark = null;
  public CANSparkMax backLeftSpark = null;

  public CANSparkMax frontRightSpark = null;
  public CANSparkMax backRightSpark = null;
  

  public DifferentialDrive dDrive = null;

  public Drivetrain() {

    frontLeftSpark = new CANSparkMax(DriveTrainConstants.DRIVETRAIN_LEFT_FRONT, MotorType.kBrushed);
    backLeftSpark = new CANSparkMax(DriveTrainConstants.DRIVETRAIN_LEFT_BACK, MotorType.kBrushed);

    backLeftSpark.follow(frontLeftSpark);

    frontRightSpark = new CANSparkMax(DriveTrainConstants.DRIVETRAIN_RIGHT_FRONT, MotorType.kBrushed);
    backRightSpark = new CANSparkMax(DriveTrainConstants.DRIVETRAIN_RIGHT_BACK, MotorType.kBrushed);

    backRightSpark.follow(frontRightSpark);

    frontRightSpark.setInverted(true);
    backRightSpark.setInverted(true);

    dDrive = new DifferentialDrive(frontLeftSpark, frontRightSpark);

  }

  public void arDrive(double speed, double turnSpeed) {
    SmartDashboard.putNumber("Drive Train Input", speed);
    SmartDashboard.putNumber("Drive Train Turn Input",turnSpeed);
    dDrive.arcadeDrive(speed, turnSpeed);
  }
  @Override
  public void periodic() {
  }
}
