package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.utils.Constants.ControllerConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    SmartDashboard.putNumber("Main Arm Position",
        RobotContainer.m_arm.getMainArmPos());
    SmartDashboard.putNumber("Mini Arm Position",
        RobotContainer.m_arm.getMiniArmPos());
    SmartDashboard.updateValues();

    GetMainArmInput();
    GetMiniArmInput();
    GetColorInput();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private void GetMainArmInput() {
    double speedArm = RobotContainer.driverController2.getLeftY();
    if (Math.abs(speedArm) >= ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.mainArmSpeed(speedArm / 3);
    } else {
      RobotContainer.m_arm.mainArmSpeed(0);
    }

    RobotContainer.m_arm.feedSpeed(
        Math.pow(RobotContainer.driverController2.getLeftTriggerAxis()
            - RobotContainer.driverController2.getRightTriggerAxis(), 3));// fancy logic moment

  }

  private void GetMiniArmInput() {

    double miniFeed = 0;

    if (RobotContainer.driverController2.getLeftBumper()) {
      if (RobotContainer.m_arm.stopSwitch.get()) {
        miniFeed = 0.7;
      } else {
        RobotContainer.driverController2.setRumble(RumbleType.kBothRumble, 0.5); //
      }
    } else if ( RobotContainer.driverController2.getRightBumper()) {
      miniFeed = -0.5;
    } else {
      miniFeed = 0;
    }

    RobotContainer.m_arm.miniFeedSpeed(miniFeed);

     double miniArm = RobotContainer.driverController2.getRightY() -miniArmOffset;

    if (Math.abs(miniArm) > ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.miniArmSpeed(miniArm / 3);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0);
    }
  }

  private void GetColorInput() {
    if (RobotContainer.driverController2.getYButton()) {
      RobotContainer.m_arm.Color('y');
    } else if (RobotContainer.driverController2.getAButton()) {
      RobotContainer.m_arm.Color('p');
    } else if (RobotContainer.driverController2.getXButton()) {
      RobotContainer.m_arm.Color('n');
    }
  }
}
