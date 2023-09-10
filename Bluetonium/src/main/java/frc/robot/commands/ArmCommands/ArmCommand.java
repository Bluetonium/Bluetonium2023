package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.utils.Constants.ledColors;//i was here
import frc.robot.RobotContainer;

public class ArmCommand extends CommandBase {

  public ArmCommand() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
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
    RobotContainer.m_arm.mainArmSpeed(speedArm / 3);

    RobotContainer.m_arm.feedSpeed(
        Math.pow(RobotContainer.driverController2.getL2Axis()
            - RobotContainer.driverController2.getR2Axis(), 3));

  }

  private void GetMiniArmInput() {

    double miniFeed = 0;

    if (RobotContainer.driverController2.getL1Button()) {
      
        miniFeed = 0.7;
     
    } else if (RobotContainer.driverController2.getR1Button()) {
      miniFeed = -0.5;
    } else {
      miniFeed = 0;
    }

    RobotContainer.m_arm.miniFeedSpeed(miniFeed);

    double miniArm = RobotContainer.driverController2.getRightY();

    RobotContainer.m_arm.miniArmSpeed(miniArm / 3);

  }

  private void GetColorInput() {
    if (RobotContainer.driverController2.getSquareButton()) {
      RobotContainer.m_arm.Color(ledColors.YELLOW);
    } else if (RobotContainer.driverController2.getCircleButton()) {
      RobotContainer.m_arm.Color(ledColors.PURPLE);
    } else if (RobotContainer.driverController2.getCrossButton()) {
      RobotContainer.m_arm.Color(ledColors.OFF);
    }
  }

}
