package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.DriveTrainCommands.DriveCommands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.Constants.ControllerConstants;

public class RobotContainer {

  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Intake m_intake = new Intake();

  public static XboxController driverController1 = new XboxController(ControllerConstants.DRIVER_CONTROLLER1);
  public static XboxController armController = new XboxController(ControllerConstants.ARM_controller);

  public RobotContainer() {
    m_drivetrain.setDefaultCommand(new DriveCommands());
    m_intake.setDefaultCommand(new intakeCommand());

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
