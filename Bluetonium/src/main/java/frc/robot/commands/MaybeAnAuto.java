package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MaybeAnAuto extends CommandBase {

  private int ticks = 0;

  public MaybeAnAuto() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (ticks < 150) {
      RobotContainer.m_drivetrain.arDrive(0, 0.6);
      ticks++;
    } else {
      RobotContainer.m_drivetrain.arDrive(0, 0);
    }

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
