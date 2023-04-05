package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.utils.Constants.ControllerConstants;

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
    if (Math.abs(speedArm) >= ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.mainArmSpeed(speedArm / 2);
    } else {
      RobotContainer.m_arm.mainArmSpeed(0);
    }

    double miniFeed = 0;
    boolean miniFeedOut = RobotContainer.driverController2.getRightBumper();

    if (RobotContainer.driverController2.getLeftBumper()) {
      if (RobotContainer.m_arm.stopSwitch.get()) {
        miniFeed = 0.5;
      } else {
        RobotContainer.driverController2.setRumble(RumbleType.kBothRumble, 1.0); // STOP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      }
    } else if (miniFeedOut) { // yeah this feed out heha
      miniFeed = -0.5;
    } else {
      miniFeed = 0;
    }

    if (RobotContainer.driverController2.getStartButton()) { // override if you care lol
      miniFeed = 0.5;
    }

    if (RobotContainer.driverController2.getBackButtonPressed()) {

      if (RobotContainer.m_arm.stopSwitch.get()) {
        SequentialCommandGroup funny = new SequentialCommandGroup(new ChangeMiniArmPos(false));
        funny.schedule();

      } else {
        // new SequentialCommandGroup(new ChangeMiniArmPos(true));

      }
    }

    RobotContainer.m_arm.miniFeedSpeed(miniFeed); // im dumbb!!!!!

    double miniArm = RobotContainer.driverController2.getRightY() - miniArmOffset;
    if (Math.abs(miniArm) > ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.miniArmSpeed(miniArm / 3);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0);
    }

    if (miniFeedOut) {
      RobotContainer.m_arm.feedSpeed(0.50);// idk check if this is feed in but lol
      // like idk
    } else {
      RobotContainer.m_arm.feedSpeed(
          Math.pow(RobotContainer.driverController2.getLeftTriggerAxis()
              - RobotContainer.driverController2.getRightTriggerAxis(), 3));// fancy logic moment
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

    // if
    // wanna rumble nerd?
    // RobotContainer.driverController1.setRumble(RumbleType.kBothRumble, 1.0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
