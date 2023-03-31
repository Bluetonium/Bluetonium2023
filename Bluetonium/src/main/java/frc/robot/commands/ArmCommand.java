package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmCommand extends CommandBase {

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
    RobotContainer.m_arm.Color('n');
  }

  @Override
  public void execute() {
    double speed = RobotContainer.driverController2.getLeftY();
    if (Math.abs(speed) >= Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.armSpeed(speed / 2);
    } else {
      RobotContainer.m_arm.armSpeed(0);
    }

    RobotContainer.m_arm.feedSpeed(
        RobotContainer.driverController2.getLeftTriggerAxis() - RobotContainer.driverController2.getRightTriggerAxis());

    if (RobotContainer.driverController2.getYButton()) {
      RobotContainer.m_arm.Color('y');
    } else if (RobotContainer.driverController2.getAButton()) {
      RobotContainer.m_arm.Color('p');
    } else if (RobotContainer.driverController2.getXButton()) {
      RobotContainer.m_arm.Color('n');
    } else if (RobotContainer.driverController2.getBButton()) {
      RobotContainer.m_arm.rainbow();
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
