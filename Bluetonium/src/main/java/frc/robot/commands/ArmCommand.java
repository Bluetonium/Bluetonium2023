package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmCommand extends CommandBase {
  private static final int direction = 1;// its the direction ig

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
    RobotContainer.m_arm.Color('n');
  }

  @Override
  public void execute() {
    double speed = RobotContainer.driverController2.getRawAxis(Constants.DRIVER_CONTROLLER2_ARMMOTOR) / 2;
    if (Math.abs(speed) >= Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.armSpeed(speed);
    } else {
      RobotContainer.m_arm.armSpeed(0);
    }

    if (RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_FEEDIN)) {
      RobotContainer.m_arm.feedSpeed(direction);
    } else if (RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_FEEDOUT)) {
      RobotContainer.m_arm.feedSpeed(-direction);
    } else {
      RobotContainer.m_arm.feedSpeed(0);
    }

    if (RobotContainer.driverController1.getYButton()
        || RobotContainer.driverController2.getRawButton(Constants.DRVIER_CONTROLLER2_YELLOW)) {
      RobotContainer.m_arm.Color('y');
    } else if (RobotContainer.driverController1.getBButton()
        || RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_PURPLE)) {
      RobotContainer.m_arm.Color('p');
    } else if (RobotContainer.driverController1.getXButton()
        || RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_NONE)) {
      RobotContainer.m_arm.Color('n');
    } else if (RobotContainer.driverController2.getRawButton(10)) {
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
