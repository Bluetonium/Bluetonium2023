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
    double moveSpeed = -RobotContainer.driverController1.getRawAxis(Constants.DRIVER_CONTROLLER1_MOVE_AXIS);
    double rotateSpeed = -RobotContainer.driverController1.getRawAxis(Constants.DRIVER_CONTROLLER1_ROTATE_AXIS);
    RobotContainer.m_drivetrain.arDrive(rotateSpeed, moveSpeed);
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
