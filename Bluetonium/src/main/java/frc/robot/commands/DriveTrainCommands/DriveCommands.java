package frc.robot.commands.DriveTrainCommands;

import frc.robot.RobotContainer;

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
    RobotContainer.m_drivetrain.arDrive(RobotContainer.driverController1.getLeftY(), RobotContainer.driverController1.getLeftX());
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