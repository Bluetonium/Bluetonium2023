// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.ADIS16470_IMU;//we will figure this out later

public class MaybeAnAuto extends CommandBase {
  private boolean balacing = false;
  private PIDController pid = new PIDController(Constants.BALCINGKP, Constants.BALCINGKI, Constants.BALCINGKD);
  private ADIS16470_IMU gyro;
  private double startAngle = 0;


  /** Creates a new MaybeAnAuto. */
  public MaybeAnAuto() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gyro = frc.robot.Robot.imu;
    startAngle = gyro.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!balacing) {
      if(Math.abs(startAngle - gyro.getAngle()) > 10) {
        balacing = true;
      }
      RobotContainer.m_drivetrain.arDrive(0, -0.5);
    } else {
      double error = gyro.getAngle();
      double pidOut = pid.calculate(error,0);
      double drivePower = pidOut/15;
      if (Math.abs(drivePower) > 0.5) {
        drivePower = Math.copySign(0.5, drivePower);
      }
      if(Math.abs(drivePower)< 0.22 && Math.abs(pidOut) < 6) {
        drivePower = Math.copySign(0.22,drivePower);
      }
      if (!(-2 < error && error < 2)) {
        RobotContainer.m_drivetrain.arDrive(0, drivePower);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
