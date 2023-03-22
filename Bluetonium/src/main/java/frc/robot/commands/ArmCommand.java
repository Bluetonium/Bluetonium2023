package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmCommand extends CommandBase {
  private static final int direction = 1;// add/remove a negative to change the direction

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double speed = RobotContainer.driverController2.getRawAxis(Constants.DRIVER_CONTROLLER2_ARMMOTOR) / 2;
    RobotContainer.m_arm.armSpeed(speed);

    if (RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_FEEDIN)) {
      RobotContainer.m_arm.feedSpeed(direction);
    } else {
      if (RobotContainer.driverController2.getRawButton(Constants.DRIVER_CONTROLLER2_FEEDOUT)) {
        RobotContainer.m_arm.feedSpeed(-direction);
      } else {
        RobotContainer.m_arm.feedSpeed(0);
      }
    }
    if (RobotContainer.driverController1.getYButton()) {
      RobotContainer.m_arm.Color("y");
    } else if (RobotContainer.driverController1.getBButton()) {
      RobotContainer.m_arm.Color("p");
    } else if (RobotContainer.driverController1.getXButton()) {
      RobotContainer.m_arm.Color("n");
    } /*
       * else if (RobotContainer.driverController1.getAButton()) {
       * RobotContainer.m_arm.Color("w");// uncomment to allow rainbow button
       * }
       */
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
