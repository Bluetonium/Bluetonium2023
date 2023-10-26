package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.commands.AutoCommands.MaybeAnAuto;
import frc.robot.commands.ArmCommands.ArmCommand;
import frc.robot.commands.DriveTrainCommands.DriveCommands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.utils.Constants.LedConstants;
import frc.robot.utils.Constants.ControllerConstants;

public class RobotContainer {

  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Arm m_arm = new Arm();

  public static PS4Controller driverController1 = new PS4Controller(ControllerConstants.DRIVER_CONTROLLER1);
  public static XboxController driverController2 = new XboxController(ControllerConstants.DRIVER_CONTROLLER2);

  public static AddressableLED m_led = new AddressableLED(LedConstants.LED_PWM_PORT);
  public static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(LedConstants.NUMBER_OF_LEDS);

  public RobotContainer() {
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(new DriveCommands());
    m_arm.setDefaultCommand(new ArmCommand());
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    return new MaybeAnAuto();
  }
}
