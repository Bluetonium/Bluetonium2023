package frc.robot.commands.DriveTrainCommands;

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
    double slowX = RobotContainer.driverController1.getLeftX();

    boolean goingSlow = Math.abs(slowY) > 0.1;

    double speed = goingSlow ? slowY : slowY;

    double rotateSpeed = slowX / 2;

    RobotContainer.m_drivetrain.arDrive(speed, rotateSpeed);

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