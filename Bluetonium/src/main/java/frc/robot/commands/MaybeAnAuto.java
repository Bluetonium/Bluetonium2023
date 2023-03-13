package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.RobotContainer;
import java.lang.Double;

public class MaybeAnAuto extends CommandBase {
  private boolean balacing = false;
  private PIDController pid = new PIDController(Constants.BALCINGKP, Constants.BALCINGKI, Constants.BALCINGKD);
  private double startAngle = 0;


  public MaybeAnAuto() {
    addRequirements(RobotContainer.m_drivetrain);
  }
  private double getPitch() {
    RobotContainer.arduino.writeString("GETPITCH");
    String response = RobotContainer.arduino.readString();
    return Double.parseDouble(response);
  }

  @Override
  public void initialize() {
    RobotContainer.arduino.writeString("GETPITCH");


    startAngle = getPitch();
  }

  @Override
  public void execute() {
    if (!balacing) {
      if (Math.abs(startAngle - getPitch()) > 10) {
        balacing = true;
      }
      RobotContainer.m_drivetrain.arDrive(0, -0.5);
    } else {
      double error = getPitch();
      double pidOut = pid.calculate(error, 0);
      double drivePower = pidOut / 15;
      if (Math.abs(drivePower) > 0.5) {
        drivePower = Math.copySign(0.5, drivePower);
      }
      if (Math.abs(drivePower) < 0.22 && Math.abs(pidOut) < 6) {
        drivePower = Math.copySign(0.22, drivePower);
      }
      if (!(-2 < error && error < 2)) {
        RobotContainer.m_drivetrain.arDrive(0, drivePower);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
