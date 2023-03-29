package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    try {
      RobotContainer.arduino = new SerialPort(115200, Constants.ARDUINO_PORT);
    } catch (Exception e) {
      RobotContainer.arduino = null;
      System.out.println("ERROR : " + e.getMessage());
    }

    RobotContainer.m_led.setLength(RobotContainer.m_ledBuffer.getLength());
    RobotContainer.m_arm.Color('s');
    RobotContainer.m_led.setData(RobotContainer.m_ledBuffer);// i think i only need to do this once
    RobotContainer.m_led.start();
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    CameraServer.startAutomaticCapture();// if this doesnt work uncomment bellow and see if it works
    CvSink cvSink = CameraServer.getVideo();
    /*
     * UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
     * MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
     * mjpegServer1.setSource(usbCamera);
     * 
     * // Creates the CvSink and connects it to the UsbCamera
     * CvSink cvSink = new CvSink("opencv_USB Camera 0");
     * cvSink.setSource(usbCamera);
     * 
     * // Creates the CvSource and MjpegServer [2] and connects them
     * CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480,
     * 30);
     * MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);
     * mjpegServer2.setSource(outputStream);
     */

  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
