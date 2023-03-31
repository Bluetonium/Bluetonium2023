package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommands extends CommandBase {
  private static double currentMoveSpeed = 0;

  public DriveCommands() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double slowY = RobotContainer.driverController1.getLeftY() / 2;
    double slowX = RobotContainer.driverController1.getLeftX() / 2;

    double targetSpeed = (Math.abs(slowY) > 0.1) ? slowY : RobotContainer.driverController1.getRightY();
    if (Math.abs(targetSpeed) < 0.1) {
      currentMoveSpeed = 0;
    } else {
      currentMoveSpeed += Math.copySign(Constants.DRIVETRAIN_RAMP_UP_SPEED, targetSpeed - currentMoveSpeed);
    }

    double rotateSpeed = (slowX != 0) ? slowX : RobotContainer.driverController1.getRightX();

    RobotContainer.m_drivetrain.arDrive(rotateSpeed, currentMoveSpeed);
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
