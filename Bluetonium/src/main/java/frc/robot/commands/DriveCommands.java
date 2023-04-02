package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommands extends CommandBase {

  public DriveCommands() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double slowY = RobotContainer.driverController1.getLeftY() * 0.7;
    double slowX = RobotContainer.driverController1.getLeftX() * 0.7;

    double speed = (Math.abs(slowY) > 0.1) ? slowY : RobotContainer.driverController1.getRightY();

    double rotateSpeed = (Math.abs(slowY) > 0.1) ? slowX : RobotContainer.driverController1.getRightX();

    RobotContainer.m_drivetrain.arDrive(rotateSpeed, speed);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.arDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}