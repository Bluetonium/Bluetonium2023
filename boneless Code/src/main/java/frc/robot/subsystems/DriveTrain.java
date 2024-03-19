package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    private CANSparkMax backLeftMotor = new CANSparkMax(Constants.driveTrain.BACK_LEFT_MOTOR, MotorType.kBrushed);
    private CANSparkMax backRightMotor = new CANSparkMax(Constants.driveTrain.BACK_RIGHT_MOTOR, MotorType.kBrushed);
    private CANSparkMax frontRightMotor = new CANSparkMax(Constants.driveTrain.FRONT_RIGHT_MOTOR, MotorType.kBrushed);
    private CANSparkMax frontLeftMotor = new CANSparkMax(Constants.driveTrain.FRONT_LEFT_MOTOR, MotorType.kBrushed);
    private DifferentialDrive drive;

    public DriveTrain() {
        drive = new DifferentialDrive(frontRightMotor, backLeftMotor);
        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

    }

    public void setSpeed(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }
}
