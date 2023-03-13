package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  public CANSparkMax arm = null;
  public CANSparkMax feed = null;
  public Arm() {
    arm = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
    feed = new CANSparkMax(Constants.FEED_MOTOR, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
  }

  public void armSpeed(double speed) {
    arm.set(speed);
  }
  public void feedSpeed(double speed) {
    feed.set(speed);
  }
}
