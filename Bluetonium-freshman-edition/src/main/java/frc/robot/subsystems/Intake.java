package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax motor1;
    private CANSparkMax motor2;

    public Intake() {
        motor1 = new CANSparkMax(Constants.ArmConstants.ARM_MOTOR1, MotorType.kBrushed);
        motor2 = new CANSparkMax(Constants.ArmConstants.ARM_MOTOR2, MotorType.kBrushed);
    }

    public void setMotor1Speed(double speed) {
        motor1.set(speed);
    }

    public void setMotor2Speed(double speed) {
        motor2.set(speed);
    }
}
