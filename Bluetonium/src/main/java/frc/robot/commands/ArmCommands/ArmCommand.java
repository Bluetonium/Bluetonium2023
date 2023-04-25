package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.utils.Constants.ControllerConstants;
//import frc.robot.commands.ArmCommands.AutoCubeIntake.MiniCubeInTakeAndTransfer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmCommand extends CommandBase {
  private double miniArmOffset = 0;
  private SequentialCommandGroup autoInTake;

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
    RobotContainer.m_arm.Color('n');
    // miniArmOffset = RobotContainer.driverController2.getRightY();
    // autoInTake = new MiniCubeInTakeAndTransfer();
  }

  @Override
  public void execute() {

    SmartDashboard.putNumber("Main Arm Position",
        RobotContainer.m_arm.getMainArmPos());
    SmartDashboard.putNumber("Mini Arm Position",
        RobotContainer.m_arm.getMiniArmPos());
    SmartDashboard.updateValues();

    double speedArm = RobotContainer.driverController2.getLeftY();
    if (Math.abs(speedArm) >= ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.mainArmSpeed(speedArm / 3);
    } else {
      RobotContainer.m_arm.mainArmSpeed(0);
    }

    double miniFeed = 0;
    boolean miniFeedOut = RobotContainer.driverController2.getRightBumper();
    if (RobotContainer.driverController2.getLeftBumper()) {
      if (RobotContainer.m_arm.stopSwitch.get()) {
        miniFeed = 0.7;
      } else {
        RobotContainer.driverController2.setRumble(RumbleType.kBothRumble, 0.5); //
        // STOP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      }
    } else if (miniFeedOut) { // yeah this feed out heha
      miniFeed = -0.5;
    } else {
      miniFeed = 0;
    }

    if (RobotContainer.driverController2.getStartButton()) { // override if you care lol
      miniFeed = 0.5;
    }

    if (RobotContainer.driverController2.getBackButtonPressed() &&
        !autoInTake.isScheduled()) {
      autoInTake.schedule();
    }

    RobotContainer.m_arm.miniFeedSpeed(miniFeed); // im dumbb!!!!!

    double miniArm = RobotContainer.driverController2.getRightY() -
        miniArmOffset;
    if (Math.abs(miniArm) > ControllerConstants.DRIVER_MINIMUM_SPEED) {
      RobotContainer.m_arm.miniArmSpeed(miniArm / 3);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0);
    }

    if (miniFeedOut)

    {
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

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
