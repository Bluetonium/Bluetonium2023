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
    double speedArm = RobotContainer.driverController2.getLeftY();
    if (Math.abs(speedArm) >= Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.mainArmSpeed(speedArm / 2);
    } else {
      RobotContainer.m_arm.mainArmSpeed(0);
    }

    double miniArm = RobotContainer.driverController2.getRightY();
    double miniFeed = (RobotContainer.driverController2.getLeftBumper()) ? 1 : 0;
    miniFeed = (RobotContainer.driverController2.getRightBumper()) ? -1 : 0;
    RobotContainer.m_arm.miniFeedSpeed(miniFeed);

    if (Math.abs(miniArm) > Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.miniArmSpeed(miniArm);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0);
    }

    if (Math.abs(miniArm) > Constants.DRIVER_MINIMUM_SPEED && miniArm > 0) {
      RobotContainer.m_arm.feedSpeed(-0.25);
    } else {
      RobotContainer.m_arm.feedSpeed(
          Math.pow(RobotContainer.driverController2.getLeftTriggerAxis()
              - RobotContainer.driverController2.getRightTriggerAxis(), 3));
    }

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
