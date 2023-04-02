package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmCommand extends CommandBase {
  private double miniArmOffset = 0;

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
    RobotContainer.m_arm.Color('n');
    miniArmOffset = RobotContainer.driverController2.getRightY();
  }

  @Override
  public void execute() {
    double speedArm = RobotContainer.driverController2.getLeftY();
    if (Math.abs(speedArm) >= Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.mainArmSpeed(speedArm / 2);
    } else {
      RobotContainer.m_arm.mainArmSpeed(0);
    }

    double miniFeed = 0;
    boolean miniFeedOut = RobotContainer.driverController2.getRightBumper();

    if (RobotContainer.driverController2.getLeftBumper()) {
      miniFeed = 0.5;// also check that this is going to feedout, who knows
    } else if (miniFeedOut) {
      miniFeed = -0.5;
    } else {
      miniFeed = 0;
    }

    RobotContainer.m_arm.miniFeedSpeed(miniFeed);

    double miniArm = RobotContainer.driverController2.getRightY() - miniArmOffset;
    if (Math.abs(miniArm) > Constants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.miniArmSpeed(miniArm / 3);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0);
    }

    if (miniFeedOut) {
      RobotContainer.m_arm.feedSpeed(0.25);// idk check if this is feed in but
      // like idk
    } else {
      RobotContainer.m_arm.feedSpeed(
          Math.pow(RobotContainer.driverController2.getLeftTriggerAxis()
              - RobotContainer.driverController2.getRightTriggerAxis(), 3) / 2);// fancy logic moment
    }

    // colors, colors everywhere
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
