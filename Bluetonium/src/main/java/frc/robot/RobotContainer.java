package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class RobotContainer {

  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Arm m_arm = new Arm();

  public static XboxController driverController1 = new XboxController(Constants.DRIVER_CONTROLLER1);
  // public static Joystick driverController2 = new
  // Joystick(Constants.DRIVER_CONTROLLER2);
  public static XboxController driverController2 = new XboxController(Constants.DRIVER_CONTROLLER2);

  public static AddressableLED m_led = new AddressableLED(Constants.LED_PWM_PORT);
  public static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(Constants.NUMBER_OF_LEDS);
  public static SerialPort arduino = null;

  public RobotContainer() {
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(new DriveCommands());
    m_arm.setDefaultCommand(new ArmCommand());
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    if (arduino != null) {
      return new MaybeAnAuto();
    }
    System.out.println("ERROR : arduino not found");// change this later when we get encoders
    return null;
  }
}
