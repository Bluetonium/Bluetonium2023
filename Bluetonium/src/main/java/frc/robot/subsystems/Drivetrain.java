package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
  public CANSparkMax frontLeftSpark = null;
  public CANSparkMax backLeftSpark = null;

  public CANSparkMax frontRightSpark = null;
  public CANSparkMax backRightSpark = null;

  public MotorControllerGroup leftMotors = null;
  public MotorControllerGroup rightMotors = null;

  public DifferentialDrive dDrive = null;
 
  public Drivetrain() {
    frontLeftSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT,MotorType.kBrushed);
    backLeftSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK,MotorType.kBrushed);

    frontRightSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT,MotorType.kBrushed);
    backRightSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK,MotorType.kBrushed);

    leftMotors = new MotorControllerGroup(frontLeftSpark,backLeftSpark);
    rightMotors = new MotorControllerGroup(frontRightSpark, backRightSpark); 

    dDrive = new DifferentialDrive(leftMotors, rightMotors);

   
   }
   public void arDrive(double speed, double turnSpeed) {
    dDrive.arcadeDrive(speed, turnSpeed);
   }

  @Override
  public void periodic() {
    
  }
}
