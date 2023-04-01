package frc.robot.commands;

//import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
///import edu.wpi.first.math.controller.PIDController;
import frc.robot.RobotContainer;
import java.lang.Double;
import java.lang.NumberFormatException;

public class MaybeAnAuto extends CommandBase {

  private boolean balacing = false;
  // private PIDController pid = null;

  private double startAngle = 0;
  private double currentAngle = 0.0;

  private int errorCount = 0;

  public MaybeAnAuto() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  private double getPitch() {
    try {
      RobotContainer.arduino.writeString("g");
      String response = RobotContainer.arduino.readString().replace("\n", "");
      return Double.parseDouble(response);
    } catch (NumberFormatException nfe) {
      errorCount++;
      System.out.println("ERROR: number format? (ig it got a invalid character?)");
      return currentAngle;
    } catch (Exception e) {
      errorCount++;
      System.out.println("ERROR : " + e.getMessage());
      return currentAngle;
    }
  }

  @Override
  public void initialize() {
    startAngle = getPitch();
    currentAngle = startAngle;
  }

  @Override
  public void execute() {
    if (RobotContainer.arduino == null || errorCount > 20) {
      RobotContainer.m_drivetrain.arDrive(0, 0);
      System.out.println("ERROR : stupid");
      return;
    } else if (balacing) {
      System.out.println("angle thing happened ig");
    }

    currentAngle = getPitch();
    if (!balacing) {
      System.out.println("Angle " + currentAngle);

      if (Math.abs(startAngle - currentAngle) > 10) {
        RobotContainer.m_drivetrain.arDrive(0, 0);
        balacing = true;
        System.out.println("Done");
      } else {
        RobotContainer.m_drivetrain.arDrive(0, 0.3);
      }
      // RobotContainer.m_drivetrain.arDrive(0, -0.5);
      System.out.println("moving backwards " + currentAngle);
    } /*
       * else {
       * 
       * double error = currentAngle;
       * System.out.println("Error " + error);
       * double pidOut = pid.calculate(error, 0);
       * double drivePower = pidOut / 15;
       * if (Math.abs(drivePower) > 0.5) {
       * drivePower = Math.copySign(0.5, drivePower);
       * }
       * if (Math.abs(drivePower) < 0.22 && Math.abs(pidOut) < 6) {
       * drivePower = Math.copySign(0.22, drivePower);
       * }
       * if (!(-2 < error && error < 2)) {
       * // RobotContainer.m_drivetrain.arDrive(0, drivePower);
       * System.out.printf("Balencing angle %.2f drive power %.2f", currentAngle,
       * drivePower);
       * }
       * 
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
